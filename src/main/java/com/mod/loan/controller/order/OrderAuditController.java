package com.mod.loan.controller.order;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.OrderAuditService;

@RestController
@RequestMapping(value = "order")
public class OrderAuditController {

    public static final Logger logger = LoggerFactory.getLogger(OrderAuditController.class);

    @Autowired
    private OrderAuditService orderAuditService;

    @RequestMapping(value = "order_audit_list")
    public ModelAndView order_audit_list(ModelAndView view) {
        view.setViewName("order/order_audit_list");
        return view;
    }

    @RequestMapping(value = "order_audit_list_ajax", method = {RequestMethod.POST})
    public ResultMessage order_audit_list_ajax(OrderAudit orderAudit, String userName, String startTime, String endTime, String userPhone, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
        param.put("status", orderAudit.getStatus() != null ? orderAudit.getStatus() : null);
        param.put("auditName", StringUtils.isNotEmpty(orderAudit.getAuditName()) ? orderAudit.getAuditName() : null);
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        return new ResultMessage(ResponseEnum.M2000, orderAuditService.findOrderAuditList(param, page), page);
    }

    @RequestMapping(value = "order_audit_name_list_ajax", method = {RequestMethod.POST})
    public ResultMessage order_audit_name_list_ajax() {
        Map<String, Object> param = new HashMap<>();
        param.put("merchant", RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, orderAuditService.findOrderAuditNameList(param));
    }

    /**
     * 跳转订单审核页面
     *
     * @param view
     * @param id
     * @param uid
     * @param orderAuditId
     * @return
     */
    @RequestMapping(value = "order_audit")
    public ModelAndView order_audit(ModelAndView view, Long id, Long uid, Long orderAuditId) {
        view.addObject("id", id);
        view.addObject("uid", uid);
        view.addObject("orderAuditId", orderAuditId);
        view.setViewName("order/order_audit");
        return view;
    }

    @RequestMapping(value = "order_audit_ajax", method = {RequestMethod.POST})
    public ResultMessage order_audit_ajax(OrderAudit orderAudit) {
        if (orderAudit.getOrderId() == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "订单id不能为空。");
        }
        if (orderAuditService.selectByPrimaryKey(orderAudit.getId()).getStatus() != 2) {
            logger.info("重复审核。orderAudit={}", JSON.toJSONString(orderAudit));
            return new ResultMessage(ResponseEnum.M4000.getCode(), "该订单已被处理。");
        }
        boolean result = orderAuditService.saveAuditResult(RequestThread.get().getMerchant(), orderAudit);
        if (result) {
            return new ResultMessage(ResponseEnum.M2000);
        } else {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "订单状态异常。");
        }
    }

}
