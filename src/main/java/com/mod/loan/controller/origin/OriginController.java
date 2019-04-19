package com.mod.loan.controller.origin;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.MerchantOrigin;
import com.mod.loan.model.Order;
import com.mod.loan.service.OriginService;
import com.mod.loan.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.mod.loan.controller.question.QuestionRefController.logger;

@RestController
@RequestMapping(value = "origin")
public class OriginController {

    @Autowired
    OriginService originService;

    @RequestMapping(value = "origin_list")
    public ModelAndView origin_list(ModelAndView mv) {
        mv.setViewName("origin/origin_list");
        return mv;
    }

    @RequestMapping(value = "origin_list_ajax")
    public ResultMessage origin_list_ajax(Page page) {
        Map<String, Object> param = new HashMap<>();
        param.put("merchant", RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, originService.findOriginList(param, page), page);
    }

    @RequestMapping(value = "origin_get")
    public ModelAndView origin_get(ModelAndView mv, MerchantOrigin merchantOrigin) {
        if (merchantOrigin.getId() == null) {
            mv.setViewName("origin/origin_add");
        } else {
            mv.addObject("id", merchantOrigin.getId());
            mv.setViewName("origin/origin_edit");
        }
        return mv;
    }

    @RequestMapping(value = "origin_add_ajax")
    public ResultMessage origin_add_ajax(MerchantOrigin merchantOrigin) {
        merchantOrigin.setCreateTime(new Date());
        merchantOrigin.setMerchant(RequestThread.get().getMerchant());
        merchantOrigin.setOriginNo(SignUtil.getUUID());
        originService.insertSelective(merchantOrigin);
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "origin_edit_ajax")
    public ResultMessage origin_edit_ajax(MerchantOrigin merchantOrigin) {
        merchantOrigin.setMerchant(RequestThread.get().getMerchant());
        originService.updateByPrimaryKeySelective(merchantOrigin);
        return new ResultMessage(ResponseEnum.M2000);
    }


    @RequestMapping(value = "manager_origin")
    public ModelAndView manager_origin(ModelAndView mv, Long managerId) {
        mv.addObject("managerId", managerId);
        mv.setViewName("origin/manager_origin");
        return mv;
    }

    @RequestMapping(value = "origin_detail_ajax")
    public ResultMessage origin_detail_ajax(MerchantOrigin merchantOrigin) {
        return new ResultMessage(ResponseEnum.M2000,originService.selectByPrimaryKey(merchantOrigin.getId()));
    }

    @RequestMapping(value = "manager_origin_list")
    public ResultMessage manager_origin_list(Long managerId) {
        Map<String, Object> data = new HashMap<String, Object>();
        List<MerchantOrigin> managerOriginList = originService.findOriginListByManagerId(managerId);
        MerchantOrigin entity = new MerchantOrigin();
        entity.setMerchant(RequestThread.get().getMerchant());
        List<MerchantOrigin> originList = originService.select(entity);
        data.put("originList", originList);
        data.put("managerOriginList", managerOriginList);
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    @RequestMapping(value = "manager_origin_update")
    public ResultMessage manager_origin_update(Long managerId, String ids) {
        if (managerId == null) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        List<Long> array = null;
        if (StringUtils.isBlank(ids)) {
            array = new ArrayList<>();
        } else {
            array = Arrays.asList(ArrayUtil.toLongArray(ids, ","));
        }
        originService.updateManagerOrigin(managerId, array);
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "my_origin_data_list")
    public ModelAndView my_origin_data_list(ModelAndView mv) {
        mv.setViewName("origin/my_origin_data_list");
        return mv;
    }

    @RequestMapping(value = "my_origin_data_ajax")
    public ResultMessage my_origin_data_ajax(Long originId, String startTime, String endTime, Page page) {
        List<Map<String, Object>> list = originService.findOriginRegisterByManagerId(originId, RequestThread.get().getUid(), startTime, endTime, page);
        list.forEach(item -> {
            item.put("user_phone", StringReplaceUtil.phoneReplaceWithStar(item.get("user_phone").toString()));
        });
        return new ResultMessage(ResponseEnum.M2000, list, page);
    }

    @RequestMapping(value = "my_origin")
    public ResultMessage my_origin_ajax() {
        return new ResultMessage(ResponseEnum.M2000, originService.findOriginListByManagerId(RequestThread.get().getUid()));
    }

    @RequestMapping(value = "origin_statistics_list")
    public ModelAndView origin_statistics_list(ModelAndView view) {
        view.setViewName("origin/origin_statistics_list");
        return view;
    }

    @RequestMapping(value = "origin_statistics_list_ajax")
    public ResultMessage origin_statistics_list_ajax() {
        Map<String, Object> param = new HashMap<>();
        param.put("merchant", RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, originService.findOriginStatistics(param));
    }

    @RequestMapping(value = "origin_order_statistics_list")
    public ModelAndView origin_order_statistics_list(ModelAndView view) {
        view.setViewName("origin/origin_order_statistics_list");
        return view;
    }

    @RequestMapping(value = "origin_order_statistics_list_ajax")
    public ResultMessage origin_order_statistics_list_ajax(Order order, String startTime, String endTime, String userOrigin) {
        int timeDiff = TimeUtils.getTimeDiff(startTime, endTime);
        if (timeDiff > 38 || timeDiff < 0) {
            return null;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("userType", order.getUserType() != null ? order.getUserType() : null);
        param.put("startTime", StringUtils.isBlank(startTime) ? TimeUtils.getNowString() : startTime);
        param.put("endTime", StringUtils.isBlank(endTime) ? TimeUtils.getNowString() : endTime);
        param.put("userOrigin", StringUtils.isNotEmpty(userOrigin) ? userOrigin : null);
        return new ResultMessage(ResponseEnum.M2000, originService.findOriginOrderList(param));
    }

    @RequestMapping(value = "current_origin_statistics_list")
    public ModelAndView current_origin_statistics_list(ModelAndView view) {
        view.setViewName("origin/current_origin_statistics_list");
        return view;
    }

    @RequestMapping(value = "current_origin_statistics_list_ajax")
    public ResultMessage current_origin_statistics_list_ajax(String originName, String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("originName", StringUtils.isNotEmpty(originName) ? originName : null);
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        param.put("managerId", RequestThread.get().getUid());
        return new ResultMessage(ResponseEnum.M2000,originService.findCurrentOriginStatisticsList(param,page),page);
    }

    @RequestMapping(value = "export_origin_order_statistics_list")
    public void export_report(String reportName, String startTime, String endTime, Integer userType, HttpServletResponse response, String userOrigin) {
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
            param.put("userOrigin", StringUtils.isNotEmpty(userOrigin) ? userOrigin : null);
            param.put("userType", userType != null ? userType : null);

            switch (reportName) {
                case "origin_order_statistics_list":
                    downloadFileName += "-渠道订单统计列表";
                    title = new String[]{"渠道", "申请订单量", "风控拒绝数", "风控拒绝率", "风控通过数", "风控通过率", "人工通过数", "人工通过率", "取消订单数", "取消订单率", "总通过数", "总通过率", "到期订单数", "逾期订单数", "当前逾期率", "当前逾期数", "首逾", "逾期三天", "逾期七天", "逾期十五天", "逾期三十天"};
                    sheetName = "订单总列表";
                    columns = new String[]{"user_origin", "order_apply_cnt", "risk_refuse_cnt", "risk_refuse_rate", "risk_pass_cnt", "risk_pass_rate", "audit_pass_cnt", "audit_pass_rate", "cancel_cnt", "cancel_rate", "order_pass_cnt", "order_pass_rate", "order_expire_cnt", "order_overdue_cnt", "current_overdue_cnt", "current_overdue_rate", "first_overdue_rate", "overdue_3_rate", "overdue_7_rate", "overdue_15_rate", "overdue_30_rate"};
                    list = originService.findOriginOrderList(param);
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
