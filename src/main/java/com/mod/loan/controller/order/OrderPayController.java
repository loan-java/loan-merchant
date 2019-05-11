package com.mod.loan.controller.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.model.OrderPay;
import com.mod.loan.service.MerchantService;
import com.mod.loan.service.OrderPayService;
import com.mod.loan.util.ExcelUtil;
import com.mod.loan.util.TimeUtils;

@RestController
@RequestMapping(value = "order")
public class OrderPayController {

    private static final Logger logger = LoggerFactory.getLogger(OrderPayController.class);

    @Autowired
    private OrderPayService orderPayService;
    @Autowired
    private RedisMapper redisMapper;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "order_pay_list")
    public ModelAndView order_pay_list(ModelAndView view, Long orderId) {
        view.addObject("orderId", orderId);
        view.setViewName("order/order_pay_list");
        return view;
    }

    @RequestMapping(value = "order_pay_detail")
    public ModelAndView order_pay_detail(ModelAndView view, Long orderId) {
        view.addObject("orderId", orderId);
        view.setViewName("order/order_pay_detail");
        return view;
    }

    @RequestMapping(value = "order_pay_list_ajax", method = {RequestMethod.POST})
    public ResultMessage order_pay_list_ajax(OrderPay orderPay, String userPhone, String startTime, String startUpdateTime, String endUpdateTime, String endTime, String payNo, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("orderId", orderPay.getOrderId() != null ? orderPay.getOrderId() : null);
        param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        param.put("startUpdateTime", StringUtils.isNotEmpty(startUpdateTime) ? startUpdateTime : null);
        param.put("endUpdateTime", StringUtils.isNotEmpty(endUpdateTime) ? endUpdateTime : null);
        param.put("payNo", StringUtils.isNotEmpty(payNo) ? payNo : null);
        param.put("payStatus", orderPay.getPayStatus() != null ? orderPay.getPayStatus() : null);
        param.put("payType", orderPay.getPayType() != null ? orderPay.getPayType() : null);
        return new ResultMessage(ResponseEnum.M2000, orderPayService.findOrderPayList(param, page), page);
    }

    @RequestMapping(value = "export_report_pay")
    public void export_report(String reportName, String startTime, String endTime, String startUpdateTime, String endUpdateTime, Integer payStatus, String userPhone, String payNo, Integer payType, HttpServletResponse response) {
    	// 安全校验
		if (redisMapper.get(RedisConst.USER_SECURITY_CODE_SECOND + RequestThread.get().getUid()) == null) {
			return;
		}
        try {
            String[] title = null;
            String sheetName = null;
            String[] columns = null;
            List<Map<String, Object>> list = null;
            String downloadFileName = TimeUtils.parseTime(new Date(), TimeUtils.dateformat4);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchant", RequestThread.get().getMerchant());
            param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
            param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
            param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
            param.put("startUpdateTime", StringUtils.isNotEmpty(startUpdateTime) ? startUpdateTime : null);
            param.put("endUpdateTime", StringUtils.isNotEmpty(endUpdateTime) ? endUpdateTime : null);
            param.put("payNo", StringUtils.isNotEmpty(payNo) ? payNo : null);
            param.put("payStatus", payStatus != null ? payStatus : null);
            param.put("payType", payType != null ? payType : null);
            switch (reportName) {
                case "order_pay_list":
                    downloadFileName += "_放款记录报表";
                    title = new String[]{"放款流水号", "用户姓名", "支付通道", "用户手机", "放款状态", "支付金额（元）", "到账金额", "到账卡号", "放款时间", "完成时间", "备注"};
                    sheetName = "放款记录报表";
                    columns = new String[]{"pay_no", "user_name", "pay_type", "user_phone", "pay_status", "pay_money", "bank", "bank_no", "create_time", "update_time", "remark"};
                    list = orderPayService.exportReport(param);
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

    }

    @RequestMapping(value = "pay_type_list", method = {RequestMethod.POST})
    public ResultMessage pay_type_list() {
        logger.info("merchant值：" + RequestThread.get().getMerchant());
        Merchant merchant = merchantService.findMerchantByAlias(RequestThread.get().getMerchant());
        if(merchant == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "配置信息不存在");
        }
        logger.info("配置信息：" + merchant.toString());
        return new ResultMessage(ResponseEnum.M2000, merchant.getMerchantChannel());

    }
}
