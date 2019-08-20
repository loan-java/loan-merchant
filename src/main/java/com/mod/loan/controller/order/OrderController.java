package com.mod.loan.controller.order;

import com.mod.loan.common.enums.OrderSourceEnum;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.*;
import com.mod.loan.util.ConstantUtils;
import com.mod.loan.util.ExcelUtil;
import com.mod.loan.util.TimeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderAuditService orderAuditService;
    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CallBackRongZeService callBackRongZeService;

    @Autowired
    private CallBackBengBengService callBackBengBengService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "order_list")
    public ModelAndView order_list(ModelAndView view) {
        view.setViewName("order/order_list");
        return view;
    }

    @RequestMapping(value = "order_pass_list")
    public ModelAndView order_pass_list(ModelAndView view) {
        view.setViewName("order/order_pass_list");
        return view;
    }

    @RequestMapping(value = "order_pre_list")
    public ModelAndView order_pre_list(ModelAndView view) {
        view.setViewName("order/order_pre_list");
        return view;
    }

    @RequestMapping(value = "order_middle_list")
    public ModelAndView order_middle_list(ModelAndView view) {
        view.setViewName("order/order_middle_list");
        return view;
    }

    @RequestMapping(value = "order_repay_for_offline_list")
    public ModelAndView order_repay_for_offline_list(ModelAndView view) {
        view.setViewName("order/order_repay_for_offline_list");
        return view;
    }

    @RequestMapping(value = "order_pass_list_ajax", method = {RequestMethod.POST})
    public ResultMessage order_pass_list_ajax(Order order, String startTime, String endTime, Page page) {
        int timeDiff = TimeUtils.getTimeDiff(startTime, endTime);
        if (timeDiff > 7 || timeDiff < 0) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("userType", order.getUserType() != null ? order.getUserType() : null);
        param.put("startTime", StringUtils.isBlank(startTime) ? TimeUtils.getNowString() : startTime);
        param.put("endTime", StringUtils.isBlank(endTime) ? TimeUtils.getNowString() : endTime);
        return new ResultMessage(ResponseEnum.M2000, orderService.findOrderPassList(param, page), page);
    }

    @RequestMapping(value = "order_list_ajax", method = {RequestMethod.POST})
    public ResultMessage order_list_ajax(Order order, String userPhone, String startTime, String endTime, String startRealRepayTime, String endRealRepayTime, String startCreateTime, String endCreateTime, Page page, String searchType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", order.getId() != null ? order.getId() : null);
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
        param.put("status", order.getStatus() != null ? order.getStatus() : null);
        param.put("borrowDay", order.getBorrowDay() != null ? order.getBorrowDay() : null);
        param.put("startTime", StringUtils.isBlank(startTime) ? null : startTime);
        param.put("endTime", StringUtils.isBlank(endTime) ? null : endTime);
        param.put("startRealRepayTime", StringUtils.isBlank(startRealRepayTime) ? null : startRealRepayTime);
        param.put("endRealRepayTime", StringUtils.isBlank(endRealRepayTime) ? null : endRealRepayTime);
        param.put("startCreateTime", StringUtils.isBlank(startCreateTime) ? null : startCreateTime);
        param.put("endCreateTime", StringUtils.isBlank(endCreateTime) ? null : endCreateTime);
        param.put("searchType", StringUtils.isBlank(searchType) ? null : searchType);
        return new ResultMessage(ResponseEnum.M2000, orderService.findOrderList(param, page), page);
    }

    @RequestMapping("/queryOrderRiskStrategyList")
    public Object queryOrderRiskStrategyList(long oid) {
        return orderService.findOrderRiskStrategyList(oid);
    }

    /**
     * 跳转信审工作台
     *
     * @param view
     * @return
     */
    @RequestMapping(value = "order_audit_workbench")
    public ModelAndView order_audit_workbench(ModelAndView view) {
        view.setViewName("order/order_audit_workbench");
        return view;
    }

    @RequestMapping(value = "order_audit_workbench_ajax", method = {RequestMethod.POST})
    public ResultMessage order_audit_workbench_ajax(Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", Constant.ORDER_FOR_AUDITING);
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("auditId", RequestThread.get().getUid());
        param.put("auditStatus", 2);// 审核记录状态：审核中
        return new ResultMessage(ResponseEnum.M2000, orderService.findWorkbenchList(param, page), page);
    }

    /**
     * 个人信审数据获取
     *
     * @return
     */
    @RequestMapping(value = "audit_order_message_ajax", method = {RequestMethod.POST})
    public ResultMessage audit_order_message_ajax() {
        Map<String, Object> data = new HashMap<String, Object>();
        Integer num = 0;
        num = orderService.countUnAuditOrder();
        data.put("syddfss", num != null ? num : 0);
        data.putAll(orderAuditService.countAuditOrderMessage());
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    /**
     * 取单
     *
     * @param getOrderNumber
     * @return
     */
    @RequestMapping(value = "take_out_order_ajax", method = {RequestMethod.POST})
    public ResultMessage take_out_order_ajax(Long getOrderNumber) {
        if (getOrderNumber == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请输入取单数。");
        }
        String merchant = RequestThread.get().getMerchant();
        if (!redisMapper.getLock(RedisConst.TAKEOUT_LOCK + merchant, 10)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "系统忙，请重试。");
        }
        orderService.saveTakeOutOrder(getOrderNumber);
        redisMapper.remove(RedisConst.TAKEOUT_LOCK + merchant);
        return new ResultMessage(ResponseEnum.M2000);
    }

    /**
     * 放款
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "order_loan_ajax", method = {RequestMethod.POST})
    public ResultMessage order_loan_ajax(Long[] ids, String payType) {
        if (ids == null || ids.length == 0) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择订单");
        }
        String merchant = RequestThread.get().getMerchant();
        Merchant record = merchantService.selectByPrimaryKey(merchant);
        String paymentType = record.getPaymentType();

        // 支付通道是否合法
        if (paymentType == null || payType == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择正确的支付通道");
        } else if (!paymentType.equalsIgnoreCase(payType)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "没有开通此支付通道");
        }

        if (!redisMapper.getLock(RedisConst.REDIS_LOCK + merchant, 10)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "系统忙，请重试。");
        }
        // 遍历放款
        for (Long id : ids) {
            try {
                orderService.orderMakeLoans(id, payType);
            } catch (Exception e) {
                logger.error("放款异常，order_id={}，error_msg={}", id, e.getMessage());
                logger.error("放款异常", e);
            }
        }
        redisMapper.remove(RedisConst.REDIS_LOCK + merchant);
        return new ResultMessage(ResponseEnum.M2000);
    }

    /**
     * 取消放款
     *
     * @param
     * @return
     */
    @RequestMapping(value = "order_cancel_ajax", method = {RequestMethod.POST})
    public ResultMessage order_cancel_ajax(Long orderId) {
        if (orderId == null || orderId == 0) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择订单。");
        }
        Order order = orderService.selectByPrimaryKey(orderId);
        if (!order.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        if (!(order.getStatus() == 12 || order.getStatus() == 21 || order.getStatus() == 23)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "订单无法取消");
        }
        Order record = new Order();
        record.setId(orderId);
        record.setStatus(Constant.ORDER_CANCLE);
        orderService.updateByPrimaryKeySelective(record);
        //更新审核状态
        OrderAudit orderAudit = new OrderAudit();
        orderAudit.setOrderId(orderId);
        orderAudit.setFailReason("取消放款");
        orderAuditService.refuseAuditResult(orderAudit);
        if (OrderSourceEnum.isRongZe(order.getSource())) {
            callBackRongZeService.pushOrderStatus(order);
        } else if (OrderSourceEnum.isBengBeng(order.getSource())) {
            callBackBengBengService.pushOrderStatus(order);
        } else {
            orderService.orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), orderService.selectByPrimaryKey(orderId));
        }
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "export_report_order_list")
    public void export_report(String reportName, String startTime, String endTime, String startRealRepayTime, String endRealRepayTime, HttpServletResponse response, String userPhone, Integer status, String startCreateTime, String endCreateTime) {
        // 安全校验
        if (redisMapper.get(RedisConst.USER_SECURITY_CODE_SECOND + RequestThread.get().getUid()) == null) {
            return;
        }
        // 导出功能
        try {
            String[] title = null;
            String sheetName = null;
            String[] columns = null;
            List<Map<String, Object>> list = null;
            String downloadFileName = TimeUtils.parseTime(new Date(), TimeUtils.dateformat4);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchant", RequestThread.get().getMerchant());
            param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
            param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
            param.put("startRealRepayTime", StringUtils.isNotEmpty(startRealRepayTime) ? startRealRepayTime : null);
            param.put("endRealRepayTime", StringUtils.isNotEmpty(endRealRepayTime) ? endRealRepayTime : null);
            param.put("startCreateTime", StringUtils.isNotEmpty(startCreateTime) ? startCreateTime : null);
            param.put("endCreateTime", StringUtils.isNotEmpty(endCreateTime) ? endCreateTime : null);
            param.put("status", status);
            param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);

            switch (reportName) {
                case "order_list":
                    downloadFileName += "-订单总列表";
                    title = new String[]{"用户姓名", "用户手机", "借款期限", "状态", "借款金额", "综合费", "实际到账金额", "逾期天数", "逾期费用", "应还款金额", "已还金额", "还款减免金额", "下单时间", "到账时间", "应还日期", "实际还款时间", "客群"};
                    sheetName = "订单总列表";
                    columns = new String[]{"user_name", "user_phone", "borrow_day", "status", "borrow_money", "total_fee", "actual_money", "overdue_day", "overdue_fee", "should_repay", "had_repay", "reduce_money", "create_time", "arrive_time", "repay_time", "real_repay_time", "user_type"};
                    list = orderService.exportReport(param);
                    break;
                default:
                    logger.info("无法导出不存在的报表:" + reportName);
                    return;
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            ExcelUtil.createSheet(workbook, sheetName, title, ExcelUtil.mapToArray(list, columns));
            ExcelUtil.excelExp(response, downloadFileName, workbook);
        } catch (Exception e) {
            logger.error(reportName + "报告导出异常。", e);
        }
        return;
    }
}
