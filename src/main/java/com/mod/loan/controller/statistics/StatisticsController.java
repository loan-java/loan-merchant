package com.mod.loan.controller.statistics;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mod.loan.service.*;
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
import com.mod.loan.util.ExcelUtil;
import com.mod.loan.util.TimeUtils;

@RestController
@RequestMapping(value = "statistics")
public class StatisticsController {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private ReportOrderLoanService reportOrderLoanService;
    @Autowired
    private ReportOrderRepayService reportOrderRepayService;
    @Autowired
    private ReportPartnerEffectService reportPartnerEffectService;
    @Autowired
    private ReportRegisterOrderService reportRegisterOrderService;

    @RequestMapping(value = "loan_report_list")
    public ModelAndView loan_report_list(ModelAndView view) {
        view.setViewName("statistics/loan_report_list");
        return view;
    }

    @RequestMapping(value = "report_register_order_list")
    public ModelAndView report_register_order_list(ModelAndView view) {
        view.setViewName("statistics/report_register_order_list_new");
        return view;
    }

    @RequestMapping(value = "report_register_order_list_ajax")
    public ResultMessage report_register_order_list_ajax(String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<>();
        param.put("merchant",RequestThread.get().getMerchant());
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        return new ResultMessage(ResponseEnum.M2000,reportRegisterOrderService.findReportRegisterOrderList(param,page),page);
    }

    @RequestMapping(value = "loan_report_list_ajax", method = {RequestMethod.POST})
    public ResultMessage loan_report_list_ajax(String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        return new ResultMessage(ResponseEnum.M2000, reportOrderLoanService.findReportOrderLoanList(param, page), page);
    }

    @RequestMapping(value = "repay_report_list")
    public ModelAndView repay_report_list(ModelAndView view) {
        view.setViewName("statistics/repay_report_list");
        return view;
    }

    @RequestMapping(value = "repay_report_list_ajax", method = {RequestMethod.POST})
    public ResultMessage repay_report_list_ajax(String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        return new ResultMessage(ResponseEnum.M2000, reportOrderRepayService.findReportOrderRepayList(param, page), page);
    }

    @RequestMapping(value = "partner_report_list")
    public ModelAndView partner_report_list(ModelAndView view) {
        view.setViewName("statistics/partner_report_list");
        return view;
    }

    @RequestMapping(value = "partner_report_list_ajax", method = {RequestMethod.POST})
    public ResultMessage partner_report_list_ajax(String userOrigin, String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("userOrigin", StringUtils.isNotEmpty(userOrigin) ? userOrigin : null);
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        return new ResultMessage(ResponseEnum.M2000, reportPartnerEffectService.findReportPartnerEffectList(param, page), page);
    }

    @RequestMapping(value = "day")
    public ModelAndView main(ModelAndView view) {
        view.setViewName("statistics/day_new");
        return view;
    }

	@RequestMapping(value = "main_data_ajax", method = { RequestMethod.POST })
	public ResultMessage main_data_ajax(String searchTime) {
		if (StringUtils.isEmpty(searchTime)) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择统计日期。");
		}
		return new ResultMessage(ResponseEnum.M2000, orderService.mainData(RequestThread.get().getMerchant(), searchTime));
	}

    @RequestMapping(value = "export_report")
    public void export_report(String reportName, String startTime, String endTime, HttpServletResponse response, String userPhone, String userOrigin, Integer status) {
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
            param.put("status", status);
            param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
            param.put("userOrigin", StringUtils.isNotEmpty(userOrigin) ? userOrigin : null);

            switch (reportName) {
                case "order_loan":
                    downloadFileName += "-借款报表";
                    // 定义excel第一行的信息
                    title = new String[]{"放款日期", "放款笔数(笔)", "放款金额(元)", "首借人数(人)", "首借金额(元)", "次新人数(人)", "次新金额(元)", "续借人数(人)", "续借金额(元)", "综合费用(元)"};
                    sheetName = "借款报表";
                    // 设置插入值的名称
                    columns = new String[]{"day_key", "arrive_cnt", "arrive_amount", "first_cnt", "first_amount", "second_cnt", "second_amount", "old_cnt", "old_amount", "total_fee"};
                    // 获取信息
                    list = reportOrderLoanService.exportReport(param);
                    break;
                case "order_repay":
                    downloadFileName += "-还款报表";
                    // 定义excel第一行的信息
                    title = new String[]{"应还日期", "应还订单", "提前还款", "正常还款", "逾期已还", "逾期中", "坏账", "应还金额", "实还金额", "放款金额/成本", "逾期费(元)", "减免金额(元)", "待还金额", "首逾率", "逾期率", "回收率1天", "回收率3天", "回收率7天", "回收率15天"};
                    sheetName = "还款报表";
                    // 设置插入值的名称
                    columns = new String[]{"day_key", "should_repay_cnt", "early_repay_cnt", "normal_repay_cnt", "overdue_repay_cnt", "overdue_cnt", "bad_cnt", "repay_amount", "real_repay_amount", "pay_amount", "overdue_fee", "reduce_money", "overdue_repay_amount", "first_overdue_rate", "overdue_rate", "overdue1_repay_cnt1", "overdue3_repay_cnt1", "overdue7_repay_cnt1", "overdue15_repay_cnt1"};
                    // 获取信息
                    list = reportOrderRepayService.exportReport(param);
                    break;
                case "partner_effect":
                    downloadFileName += "-渠道统计";
                    // 定义excel第一行的信息
                    title = new String[]{"注册日期", "注册渠道", "注册人数(人)", "注册的登录数量(人)", "实名人数(人)", "提单人数(人)", "首借人数(人)", "首借金额(元)"};
                    sheetName = "渠道统计";
                    // 设置插入值的名称
                    columns = new String[]{"day_key", "user_origin", "reg_cnt", "login_cnt", "real_name_cnt", "submit_order_cnt", "first_submit_cnt", "first_submit_amount"};
                    // 获取信息
                    list = reportPartnerEffectService.exportReport(param);
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

}
