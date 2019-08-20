package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.enums.OrderSourceEnum;
import com.mod.loan.common.enums.PbResultEnum;
import com.mod.loan.common.enums.PolicyResultEnum;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.*;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Merchant;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.CallBackBengBengService;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.OrderAuditService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderAuditServiceImpl extends BaseServiceImpl<OrderAudit, Long> implements OrderAuditService {

    public static final Logger logger = LoggerFactory.getLogger(OrderAuditServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAuditMapper orderAuditMapper;

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CallBackRongZeService callBackRongZeService;

    @Autowired
    private CallBackBengBengService callBackBengBengService;

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

        Merchant merchant1 = merchantMapper.selectByPrimaryKey(merchant);
        if (merchant1 == null) {
            logger.error("商户不存在,order={}", JSON.toJSONString(order));
            return false;
        }
        Integer riskType = merchant1.getRiskType();
        if (riskType == null) riskType = 2;

        // 复审通过
        String riskCode = "", riskDesc = "";
        if (orderAudit.getStatus() == 0) {
            if (riskType == 1) {
                riskCode = PolicyResultEnum.AGREE.getCode();
            } else if (riskType == 2) {
                riskCode = PbResultEnum.APPROVE.getCode();
            } else {
                riskCode = "0";
            }
            order.setAuditTime(new Date());
            order.setStatus(Constant.ORDER_FOR_LENDING);
            orderMapper.updateByPrimaryKey(order);
            orderAudit.setCreteTime(new Date());
            orderAuditMapper.updateByPrimaryKeySelective(orderAudit);
        } else if (orderAudit.getStatus() == 1) {// 复审拒绝
            if (riskType == 1) {
                riskCode = PolicyResultEnum.REJECT.getCode();
            } else if (riskType == 2) {
                riskCode = PbResultEnum.DENY.getCode();
            } else {
                riskCode = "-1";
            }
            riskDesc = "人工审核拒绝";
            order.setAuditTime(new Date());
            order.setStatus(Constant.ORDER_AUDIT_FAIL);
            orderMapper.updateByPrimaryKey(order);
            // 更新审核记录
            orderAudit.setCreteTime(new Date());
            orderAuditMapper.updateByPrimaryKeySelective(orderAudit);
        }
        if (OrderSourceEnum.isRongZe(order.getSource())) {
            callBackRongZeService.pushOrderStatus(order);
        } else if (OrderSourceEnum.isBengBeng(order.getSource())) {
            callBackBengBengService.pushOrderStatus(order);
        } else {
            orderService.orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), orderMapper.selectByPrimaryKey(orderAudit.getOrderId()));
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

    @Override
    public int refuseAuditResult(OrderAudit orderAudit) {
        Manager manager = managerMapper.selectByPrimaryKey(RequestThread.get().getUid());
        orderAudit.setAuditId(manager.getId());
        orderAudit.setAuditName(manager.getUserName());
        return orderAuditMapper.refuseAuditResult(orderAudit);
    }

}
