package com.mod.loan.controller.user;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.User;

@RestController
@RequestMapping(value = "user")
public class UserAddressListController {

	public static final Logger logger = LoggerFactory.getLogger(UserAddressListController.class);
	
	@Autowired
	private UserMapper userMapper;
	

	@RequestMapping(value = "user_address_list_detail")
	public ModelAndView user_address_list_detail(ModelAndView view, Long id) {
		view.addObject("id", id);
		view.setViewName("user/user_address_list_detail");
		return view;
	}

	@RequestMapping(value = "user_address_list_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage user_address_list_detail_ajax(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		if (user != null && RequestThread.get().getMerchant().equals(user.getMerchant())) {
			try {
				String url = Constant.server_itf_url + "user/address_list?uid=" + id;
				Response execute = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).execute();
				return new ResultMessage(ResponseEnum.M2000, execute.body());
			} catch (IOException e) {
				logger.error("从itf获取通讯录失败，uid={}", id);
			}
		}
		return new ResultMessage(ResponseEnum.M4000);
	}

}
