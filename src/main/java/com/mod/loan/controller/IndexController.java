package com.mod.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.model.RequestThread;
import com.mod.loan.model.Manager;
import com.mod.loan.service.ManagerService;
import com.mod.loan.util.StringReplaceUtil;

@RestController
public class IndexController {

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("redirect:/system/index");
		return mv;
	}

	@RequestMapping(value = "/user_security")
	public ModelAndView user_security(ModelAndView mv) {
		Manager manager = managerService.selectByPrimaryKey(RequestThread.get().getUid());
		mv.addObject("user_phone", StringReplaceUtil.phoneReplaceWithStar(manager.getUserPhone()));
		mv.setViewName("user_security");
		return mv;
	}
}
