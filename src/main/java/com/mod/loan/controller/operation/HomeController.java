package com.mod.loan.controller.operation;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Home;
import com.mod.loan.service.HomeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by chenanle on 2018/7/9.
 */
@RestController
@RequestMapping(value = "operation")
public class HomeController {

	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@RequestMapping(value = "home_list")
	public ModelAndView home_list(ModelAndView view) {
		view.setViewName("operation/home_list");
		return view;
	}

	@RequestMapping(value = "home_list_ajax", method = { RequestMethod.POST })
	public ResultMessage home_list_ajax(Home home, Page page) {
		home.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, homeService.findHomeList(home, page), page);
	}

	@RequestMapping(value = "home_edit")
	public ModelAndView home_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/home_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/home_edit");
		}
		return view;
	}

	@RequestMapping(value = "home_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage home_edit_ajax(Home home) {
		home.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(home.getImgurl())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "图片不能为空");
		}
		if (home.getId() == null) {
			home.setCreateTime(new Date());
			home.setUpdateTime(new Date());
			homeService.insertSelective(home);
		} else {
			home.setUpdateTime(new Date());
			homeService.updateByPrimaryKeySelective(home);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "home_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage home_detail_ajax(Home home) {
		home.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, homeService.selectOne(home));
	}

}
