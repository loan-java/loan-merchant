package com.mod.loan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mod.loan.mapper.UserMapper;
import com.mod.loan.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.OrderAuditMapper;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.OrderAuditService;

@Service
public class OrderAuditServiceImpl extends BaseServiceImpl<OrderAudit, Long> implements OrderAuditService {

	public static final Logger logger = LoggerFactory.getLogger(OrderAuditServiceImpl.class);
	
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAuditMapper orderAuditMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> findOrderAuditList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderAuditMapper.orderAuditCount(param));
        return orderAuditMapper.findOrderAuditList(param);
    }

    @Override
    public List<Map<String, Object>> findOrderAuditNameList(Map<String, Object> param) {
        return orderAuditMapper.findOrderAuditNameList(param);
    }

    @Override
	public boolean saveAuditResult(String merchant, OrderAudit orderAudit) {
		Order order = orderMapper.selectByPrimaryKey(orderAudit.getOrderId());
		if (!order.getMerchant().equals(merchant)) {
			logger.error("订单跨商户处理,merchant={},order={}", merchant, JSON.toJSONString(order));
			return false;
		}
		if (order.getStatus() != Constant.ORDER_FOR_AUDITING) {
			logger.error("订单状态异常,自动取消审核记录,order={}", JSON.toJSONString(order));
			return false;
		}
        // 复审通过
        if (orderAudit.getStatus() == 0) {
            order.setAuditTime(new Date());
            order.setStatus(Constant.ORDER_FOR_LENDING);
            orderMapper.updateByPrimaryKey(order);
            orderService.orderCallBack(userMapper.selectByPrimaryKey(order.getUid()),order.getOrderNo(),order.getStatus());
            orderAudit.setCreteTime(new Date());
            orderAuditMapper.updateByPrimaryKeySelective(orderAudit);
        } else if (orderAudit.getStatus() == 1) {// 复审拒绝
            order.setAuditTime(new Date());
            order.setStatus(Constant.ORDER_AUDIT_FAIL);
            orderMapper.updateByPrimaryKey(order);
            orderService.orderCallBack(userMapper.selectByPrimaryKey(order.getUid()),order.getOrderNo(),order.getStatus());
            // 更新审核记录
            orderAudit.setCreteTime(new Date());
            orderAuditMapper.updateByPrimaryKeySelective(orderAudit);
        }
        return true;
    }

    @Override
    public Map<String, Object> countAuditOrderMessage() {
        Map<String, Object> param = new HashMap<>();
        param.put("auditId", RequestThread.get().getUid());
        param.put("merchant", RequestThread.get().getMerchant());
        return orderAuditMapper.countAuditOrderMessage(param);
    }

}
