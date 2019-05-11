package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.enums.OrderStatusEnum;
import com.mod.loan.common.enums.OrderTypeEnum;
import com.mod.loan.common.enums.PayStatusEnum;
import com.mod.loan.common.enums.RepayStatusEnum;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.*;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.model.User;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.juhe.CallBackJuHeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MerchantRateMapper merchantRateMapper;

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
                orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), order.getOrderNo(), order.getStatus());
                // 发送消息
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderId", id);
                jsonObject.put("payType", payType);
                System.out.println("放款类型：" + Constant.payType);
                System.out.println("==========================================");
                if (Constant.payType.equals("baofoo")) {
                    rabbitTemplate.convertAndSend(RabbitConst.baofoo_queue_order_pay, jsonObject);
                } else if (Constant.payType.equals("kuaiqian")) {
                    rabbitTemplate.convertAndSend(RabbitConst.kuaiqian_queue_order_pay, jsonObject);
                }
            }
        }
    }

    @Override
    public Map<String, Object> mainData(String merchant, String searchTime) {
        Map<String, Object> data = redisMapper.get(RedisConst.MAIN_STATISTICS + merchant + searchTime, new TypeReference<Map<String, Object>>() {
        });
        if (data == null) {
            data = new HashMap<>();
            data.put("merchantRate", merchantRateMapper.findByMerchant(RequestThread.get().getMerchant()));
            data.put("countRegisterUserNumberToDay", userMapper.countRegisterUserNumberToDay(merchant, searchTime));
            data.put("countRealNameUserNumberToDay", userMapper.countRealNameUserNumberToDay(merchant, searchTime));
            data.put("countUserDetailsUserNumberToDay", userMapper.countUserDetailsUserNumberToDay(merchant, searchTime));
            data.put("countMobileUserNumberToDay", userMapper.countMobileUserNumberToDay(merchant, searchTime));
            data.put("countBindbankUserNumberToDay", userMapper.countBindbankUserNumberToDay(merchant, searchTime));
            data.put("countFlowAmount", orderMapper.countFlowAmount(merchant));
            data.put("countOverdueAmount", orderMapper.countOverdueAmount(merchant));
            data.put("otherFee", orderMapper.otherFee(merchant));
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

    public void orderCallBack(User user, String orderNo, Integer orderStatus) {

        JSONObject object = JSONObject.parseObject(user.getCommonInfo());
        object.put("orderNo", orderNo);
        object.put("orderType", OrderTypeEnum.JK.getCode());
        switch (orderStatus) {
            case 21:
                object.put("orderStatus", OrderStatusEnum.WAIT_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 22:
                object.put("orderStatus", OrderStatusEnum.TO_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 23:
                object.put("orderStatus", OrderStatusEnum.PAY_FAILED.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 31:
                object.put("orderStatus", OrderStatusEnum.PAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYING.getCode());
                break;
            case 33:
                object.put("orderStatus", OrderStatusEnum.OVERDUE.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 34:
                object.put("orderStatus", OrderStatusEnum.BAD_DEBT.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 41:
                object.put("orderStatus", OrderStatusEnum.REPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYED.getCode());
            case 42:
                object.put("orderStatus", OrderStatusEnum.OVERDUEREPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.OVERDUE_REPAY.getCode());
            case 51:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 52:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 53:
                object.put("orderStatus", OrderStatusEnum.CANCEL.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            default:
                break;
        }

        CallBackJuHeUtil.callBack(Constant.juheCallBackUrl, object);
    }

}
