package com.mod.loan.controller.operation;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Startup;
import com.mod.loan.service.StartupService;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "operation")
public class StartupController {

	public static final Logger logger = LoggerFactory.getLogger(StartupController.class);

	@Autowired
	private StartupService startupService;

	@RequestMapping(value = "startup_list")
	public ModelAndView startup_list(ModelAndView view) {
		view.setViewName("operation/startup_list");
		return view;
	}

	@RequestMapping(value = "startup_list_ajax", method = { RequestMethod.POST })
	public ResultMessage startup_list_ajax(Startup startup, Page page) {
		startup.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, startupService.findStartupList(startup, page), page);
	}

	@RequestMapping(value = "startup_edit")
	public ModelAndView startup_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/startup_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/startup_edit");
		}
		return view;
	}

	@RequestMapping(value = "startup_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage startup_edit_ajax(Startup startup) {
		startup.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(startup.getAdImgurl())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "启动页图片不能为空");
		}
		if (startup.getAdIdx() == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "排序不能为空");
		}
		if (startup.getId() == null) {
			startup.setCreateTime(new Date());
			startupService.insertSelective(startup);
		} else {
			startupService.updateByPrimaryKeySelective(startup);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "startup_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage startup_detail_ajax(Startup startup) {
		startup.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, startupService.selectOne(startup));
	}

}
