package com.mod.loan.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.mapper.UserAddressListMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.User;
import com.mod.loan.model.UserAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "user")
public class UserAddressListController {

    public static final Logger logger = LoggerFactory.getLogger(UserAddressListController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressListMapper userAddressListMapper;


    @RequestMapping(value = "user_address_list_detail")
    public ModelAndView user_address_list_detail(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("user/user_address_list_detail");
        return view;
    }

    @RequestMapping(value = "user_address_list_detail_ajax", method = {RequestMethod.POST})
    public ResultMessage user_address_list_detail_ajax(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null && RequestThread.get().getMerchant().equals(user.getMerchant())) {
            UserAddressList addressList = userAddressListMapper.findByUid(user.getId());
            JSONObject object = JSON.parseObject(addressList.getAddressList());
            return new ResultMessage(ResponseEnum.M2000, object);
        }
        return new ResultMessage(ResponseEnum.M4000);
    }

}
