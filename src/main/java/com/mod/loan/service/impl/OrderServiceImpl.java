package com.mod.loan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.Constant;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.ManagerMapper;
import com.mod.loan.mapper.OrderAuditMapper;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.OrderRecycleLogMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.OrderService;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private OrderAuditMapper orderAuditMapper;
    @Autowired
    OrderRecycleLogMapper orderRecycleLogMapper;

    @Override
    public void updateOrderFollowUser(Long followUserId, String merchant, Long... ids) {
        if (ids == null | ids.length == 0) {
            throw new RuntimeException("ids is null or length is 0");
        }
        orderMapper.updateOrderFollowUser(followUserId, merchant, ids);
        orderRecycleLogMapper.insertOrderRecycleLog(followUserId, merchant, ids);
    }

    @Override
    public List<Map<String, Object>> findOrderList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderMapper.orderCount(param));
        return orderMapper.findOrderList(param);
    }

    @Override
    public List<Map<String, Object>> findOrderPassList(Map<String, Object> param, Page page) {
    	String key = "%s:%s:%s:%s";
		key = String.format(key, param.get("merchant"), param.get("userType"), param.get("startTime"), param.get("endTime"));
		List<Map<String, Object>> data = redisMapper.get(RedisConst.ORDER_PASS_STATISTICS + key, new TypeReference<List<Map<String, Object>>>() {
		});
		if (data == null) {
			data = orderMapper.findOrderPassList(param);
			redisMapper.set(RedisConst.ORDER_PASS_STATISTICS + key, data, 180);
		}
		return data;
    }

    @Override
    public List<Map<String, Object>> findWorkbenchList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderMapper.WorkbenchCount(param));
        return orderMapper.findWorkbenchList(param);
    }

    @Override
    public List<Map<String, Object>> exportReport(Map<String, Object> param) {
        List<Map<String, Object>> orderList = orderMapper.exportReport(param);
        for (Map<String, Object> map : orderList) {
            if (map.get("user_type").equals(1)) {
                map.put("user_type", "新客");
            }
            if (map.get("user_type").equals(2)) {
                map.put("user_type", "次新");
            }
            if (map.get("user_type").equals(3)) {
                map.put("user_type", "老客");
            }
            if (map.get("status").equals(11)) {
                map.put("status", "机审中");
            }
            if (map.get("status").equals(12)) {
                map.put("status", "等待复审");
            }
            if (map.get("status").equals(21)) {
                map.put("status", "待放款");
            }
            if (map.get("status").equals(22)) {
                map.put("status", "放款中");
            }
            if (map.get("status").equals(23)) {
                map.put("status", "放款失败");
            }
            if (map.get("status").equals(31)) {
                map.put("status", "还款中");
            }
            if (map.get("status").equals(32)) {
                map.put("status", "还款确认中");
            }
            if (map.get("status").equals(33)) {
                map.put("status", "逾期");
            }
            if (map.get("status").equals(34)) {
                map.put("status", "坏账");
            }
            if (map.get("status").equals(41)) {
                map.put("status", "已结清");
            }
            if (map.get("status").equals(42)) {
                map.put("status", "逾期还款");
            }
            if (map.get("status").equals(51)) {
                map.put("status", "自动审核失败");
            }
            if (map.get("status").equals(52)) {
                map.put("status", "复审失败");
            }
            if (map.get("status").equals(53)) {
                map.put("status", "取消");
            }
        }
        return orderList;
    }

    @Override
    public List<Long> findUnAuditOrder(Map<String, Object> param) {
        return orderMapper.findUnAuditOrder(param);
    }

    @Override
    public Integer countUnAuditOrder() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("status", Constant.ORDER_FOR_AUDITING);
        return orderMapper.countUnAuditOrder(param);
    }

    @Override
    public void orderMakeLoans(Long id, String payType) {
        Order order = orderMapper.selectOrderById(id);
        if (order.getMerchant().equals(RequestThread.get().getMerchant())) {
            if (order.getStatus() == Constant.ORDER_FOR_LENDING || order.getStatus() == Constant.ORDER_LEND_FAIL) {
                // 修改订单状态
                order.setStatus(Constant.ORDER_IN_LENDING);
                orderMapper.updateByPrimaryKey(order);
                // 发送消息
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderId", id);
                jsonObject.put("payType", payType);
                rabbitTemplate.convertAndSend("queue_order_pay", jsonObject);
            }
        }
    }

    @Override
    public Map<String, Object> mainData(String merchant, String searchTime) {
        Map<String, Object> data = redisMapper.get(RedisConst.MAIN_STATISTICS + merchant + searchTime, new TypeReference<Map<String, Object>>() {
        });
        if (data == null) {
            data = new HashMap<>();
            data.put("countRegisterUserNumberToDay", userMapper.countRegisterUserNumberToDay(merchant, searchTime));
            data.put("countRealNameUserNumberToDay", userMapper.countRealNameUserNumberToDay(merchant, searchTime));
            data.put("countUserDetailsUserNumberToDay", userMapper.countUserDetailsUserNumberToDay(merchant, searchTime));
            data.put("countMobileUserNumberToDay", userMapper.countMobileUserNumberToDay(merchant, searchTime));
            data.put("countAlipayUserNumberToDay", userMapper.countAlipayUserNumberToDay(merchant, searchTime));
            data.put("countBindbankUserNumberToDay", userMapper.countBindbankUserNumberToDay(merchant, searchTime));
            data.putAll(orderMapper.countOrderMessageByDay(merchant, searchTime));
            redisMapper.set(RedisConst.MAIN_STATISTICS + merchant + searchTime, data, 900);
        }
        return data;
    }

    @Override
    public void saveTakeOutOrder(Long getOrderNumber) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("status", Constant.ORDER_FOR_AUDITING);
        param.put("getOrderNumber", getOrderNumber != null ? getOrderNumber : 10);
        List<Long> orderIds = orderMapper.findUnAuditOrder(param);
        // 插入审核记录
        OrderAudit orderAudit = null;
        Manager manager = managerMapper.selectByPrimaryKey(RequestThread.get().getUid());
        for (Long id : orderIds) {
            orderAudit = new OrderAudit();
            orderAudit.setOrderId(id);
            orderAudit.setAuditId(manager.getId());
            orderAudit.setAuditName(manager.getUserName());
            orderAudit.setStatus(2); // 审核中
            orderAudit.setCreteTime(new Date());
            orderAudit.setMerchant(RequestThread.get().getMerchant());
            orderAuditMapper.insertSelective(orderAudit);
        }
    }

}
