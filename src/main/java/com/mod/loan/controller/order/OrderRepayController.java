package com.mod.loan.controller.order;

import java.math.BigDecimal;
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
import com.mod.loan.config.Constant;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.service.OrderRepayService;
import com.mod.loan.util.ExcelUtil;
import com.mod.loan.util.StringUtil;
import com.mod.loan.util.TimeUtils;

@RestController
@RequestMapping(value = "order")
public class OrderRepayController {

	public static final Logger logger = LoggerFactory.getLogger(OrderRepayController.class);

	@Autowired
	private OrderRepayService orderRepayService;
	@Autowired
	private RedisMapper redisMapper;

	@Autowired
	private OrderMapper orderMapper;

	@RequestMapping(value = "order_repay_list")
	public ModelAndView order_repay_list(ModelAndView view) {
		view.setViewName("order/order_repay_list");
		return view;
	}

	@RequestMapping(value = "order_repay_user_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage order_repay_user_detail_ajax(Order order) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", order.getId() != null ? order.getId() : null);
		return new ResultMessage(ResponseEnum.M2000, orderMapper.findUserByOrderId(param));
	}

	@RequestMapping(value = "order_repay_list_ajax", method = { RequestMethod.POST })
	public ResultMessage order_repay_list_ajax(OrderRepay orderRepay, String userPhone, String startTime, String endTime, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchant", RequestThread.get().getMerchant());
		param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
		param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
		param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
		param.put("repayType", orderRepay.getRepayType() != null ? orderRepay.getRepayType() : null);
		param.put("repayStatus", orderRepay.getRepayStatus() != null ? orderRepay.getRepayStatus() : null);
		return new ResultMessage(ResponseEnum.M2000, orderRepayService.findOrderRepayList(param, page), page);
	}

	@RequestMapping(value = "order_repay_for_offline")
	public ModelAndView order_repay_for_offline(ModelAndView view, Long id) {
		view.addObject("id", id);
		view.setViewName("order/order_repay_for_offline");
		return view;
	}

	/**
	 * 线下还款
	 *
	 * @param orderId
	 * @param repayMoney
	 * @param remark
	 * @param repayCert
	 * @return
	 */
	@RequestMapping(value = "order_repay_for_offline_ajax", method = { RequestMethod.POST })
	public ResultMessage order_repay_for_offline_ajax(Long orderId, BigDecimal repayMoney, String remark, String repayCert) {
		if (orderId == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "订单不存在");
		}
		if (StringUtils.isBlank(repayCert)) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "还款凭证不能为空。");
		}
		if (repayMoney == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "还款金额不能为空。");
		}
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "订单不存在");
		}
		if (!order.getMerchant().equals(RequestThread.get().getMerchant())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "不能操作该订单");
		}
		if (order.getShouldRepay().compareTo(repayMoney) != 0) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "还款金额和应还金额不符");
		}
		// 改变订单状态
		Order record = new Order();
		if (order.getStatus() == Constant.ORDER_IN_REPAYING) {
			record.setStatus(Constant.ORDER_REPAYED);
		} else if (order.getStatus() == Constant.ORDER_IS_OVERDUE || order.getStatus() == Constant.ORDER_BAD_LOAN) {
			record.setStatus(Constant.ORDER_OVERDUE_REPAYED);
		} else {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "当前订单状态不能线下还款");
		}
		record.setId(order.getId());
		record.setHadRepay(repayMoney);
		record.setRealRepayTime(new Date());
		OrderRepay orderRepay = new OrderRepay();
		// 插入还款记录
		String serials_no = StringUtil.getPayNumber("r");
		orderRepay.setUid(order.getUid());
		orderRepay.setOrderId(orderId);
		orderRepay.setRepayNo(serials_no);
		orderRepay.setRepayType(4);
		orderRepay.setRepayStatus(3);
		orderRepay.setRepayCert(repayCert);
		orderRepay.setRepayMoney(repayMoney);
		orderRepay.setRemark(remark);
		orderRepay.setUpdateTime(new Date());
		orderRepayService.updateOrderOffline(record, orderRepay);
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "export_report_repay")
	public void export_report(String reportName, String startTime, String endTime, Integer repayStatus, String userPhone, Integer repayType, HttpServletResponse response) {
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
			param.put("repayStatus", repayStatus != null ? repayStatus : null);
			param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
			param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
			param.put("userPhone", StringUtils.isNotEmpty(userPhone) ? userPhone : null);
			param.put("repayType", repayType != null ? repayType : null);
			switch (reportName) {
			case "order_repay_list":
				downloadFileName += "_还款记录报表";
				title = new String[] { "还款流水号", "用户姓名", "用户手机", "还款类型", "还款状态", "支付金额（元）", "还款银行", "还款卡号", "备注", "还款时间" };
				sheetName = "还款款记录报表";
				columns = new String[] { "repay_no", "user_name", "user_phone", "repay_type", "repay_status", "repay_money", "bank", "bank_no", "remark", "create_time" };
				list = orderRepayService.exportReport(param);
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
