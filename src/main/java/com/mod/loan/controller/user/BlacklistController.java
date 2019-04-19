package com.mod.loan.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
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
import com.mod.loan.model.Blacklist;
import com.mod.loan.model.User;
import com.mod.loan.service.BlacklistService;
import com.mod.loan.service.UserService;

@RestController
@RequestMapping(value = "user")
public class BlacklistController {

	public static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

	@Autowired
	BlacklistService blacklistService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "blacklist_list")
	public ModelAndView blacklist_list(ModelAndView view) {
		view.setViewName("user/blacklist_list");
		return view;
	}

	@RequestMapping(value = "blacklist_list_ajax", method = { RequestMethod.POST })
	public ResultMessage blacklist_list_ajax(Blacklist blacklist, Page page, String startTime, String endTime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchant", RequestThread.get().getMerchant());
		param.put("name", StringUtils.isNotEmpty(blacklist.getName()) ? blacklist.getName() : null);
		param.put("tel", StringUtils.isNotEmpty(blacklist.getTel()) ? blacklist.getTel() : null);
		param.put("type", blacklist.getType() != null ? blacklist.getType() : null);
		param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
		return new ResultMessage(ResponseEnum.M2000, blacklistService.findBlacklistList(param, page), page);
	}

	@RequestMapping(value = "blacklist_edit")
	public ModelAndView blacklist_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("user/blacklist_add");
		} else {
			view.addObject("id", id);
			view.setViewName("user/blacklist_edit");
		}
		return view;
	}

	@RequestMapping(value = "blacklist_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage blacklist_edit_ajax(Blacklist blacklist, Integer days) {
		if (StringUtils.isBlank(blacklist.getName())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择用户。");
		}
		// 只有灰名单有失效时间，小黑屋概念
		if (blacklist.getType() == 1 && days == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请输入关小黑屋天数。");
		}
		if (blacklist.getType() == 1) {
			blacklist.setInvalidTime(new DateTime().plusDays(days).toDate());
		}
		// 判断用户是否有记录
		User user = userService.selectByPrimaryKey(blacklist.getUid());
		if (user == null || !user.getMerchant().equals(RequestThread.get().getMerchant())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "用户不存在");
		}
		blacklist.setMerchant(user.getMerchant());
		if (blacklist.getId() == null) {
			boolean judge = blacklistService.addBlacklist(blacklist);
			if (!judge) {
				return new ResultMessage(ResponseEnum.M4000.getCode(), "该用户已加入黑名单");
			}
		} else {
			blacklistService.updateByPrimaryKeySelective(blacklist);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "blacklist_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage blacklist_detail_ajax(Long id) {
		return new ResultMessage(ResponseEnum.M2000, blacklistService.selectByPrimaryKey(id));
	}

}
