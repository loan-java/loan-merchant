package com.mod.loan.controller.merchant;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.MerchantRate;
import com.mod.loan.service.MerchantRateService;

@RestController
@RequestMapping(value = "merchant")
public class MerchantRateController {

	@Autowired
	private MerchantRateService merchantRateService;

	@RequestMapping(value = "merchant_rate_list")
	public ModelAndView merchant_rate_list(ModelAndView view) {
		view.setViewName("merchant/merchant_rate_list");
		return view;
	}

	@RequestMapping(value = "merchant_rate_list_ajax", method = { RequestMethod.POST })
	public ResultMessage merchant_rate_list_ajax(MerchantRate merchantRate, Page page) {
		return new ResultMessage(ResponseEnum.M2000, merchantRateService.findMerchantRateList(merchantRate, page),
				page);
	}

	@RequestMapping(value = "merchant_rate_edit")
	public ModelAndView merchant_rate_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("merchant/merchant_rate_add");
		} else {
			view.addObject("id", id);
			view.setViewName("merchant/merchant_rate_edit");
		}
		return view;
	}

	@RequestMapping(value = "merchant_rate_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage merchant_rate_edit_ajax(MerchantRate merchantRate) {
		if (StringUtils.isBlank(merchantRate.getProductName())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "产品名称不能为空");
		}
		if (merchantRate.getProductDay() == null || merchantRate.getProductDay() != 7) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "产品期限限定为7天");
		}
		if (merchantRate.getProductMoney() == null
				|| new BigDecimal(2000).compareTo(merchantRate.getProductMoney()) < 0) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "借款金额不能大于2000元");
		}
		if (merchantRate.getTotalRate() == null || new BigDecimal(30).compareTo(merchantRate.getTotalRate()) < 0) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "综合费率不能大于30%");
		}
		if (merchantRate.getOverdueRate() == null || new BigDecimal(6).compareTo(merchantRate.getOverdueRate()) < 0) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "逾期费率不能大于6%");
		}
		if (merchantRate.getBorrowType() == null
				|| (merchantRate.getBorrowType() > 4 && merchantRate.getBorrowType() != 99)) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请重新输入借款次数");
		}
		if (merchantRate.getId() == null) {
			if(merchantRateService.selectByBorrowType(merchantRate.getBorrowType()) > 0) {
				return new ResultMessage(ResponseEnum.M4000.getCode(), "已存在该类型费率");
			}
			merchantRate.setProductLevel(1);
			merchantRate.setMerchant(RequestThread.get().getMerchant());
			merchantRateService.insertSelective(merchantRate);
		} else if (!merchantRateService.selectByPrimaryKey(merchantRate.getId()).getMerchant().equals(RequestThread.get().getMerchant())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请重新修改");
		} else {
			merchantRateService.updateByPrimaryKeySelective(merchantRate);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "merchant_rate_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage merchant_rate_detail_ajax(Long id) {
		MerchantRate merchantRate = merchantRateService.selectByPrimaryKey(id);
		if(RequestThread.get().getMerchant().equals(merchantRate.getMerchant())) {
			return new ResultMessage(ResponseEnum.M2000, merchantRate);
		}
		return new ResultMessage(ResponseEnum.M4000);
	}

}
