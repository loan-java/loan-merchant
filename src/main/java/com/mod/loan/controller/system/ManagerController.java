package com.mod.loan.controller.system;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.RoleMapper;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Role;
import com.mod.loan.service.ManagerService;
import com.mod.loan.util.CheckUtils;
import com.mod.loan.util.HttpUtils;
import com.mod.loan.util.MD5;
import com.mod.loan.util.RandomUtils;
import com.mod.loan.util.StringUtil;

@RestController
@RequestMapping(value = "system")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisMapper redisMapper;

    @RequestMapping(value = "manager_list")
    public ModelAndView manager_list(ModelAndView view) {
        view.setViewName("system/manager_list");
        return view;
    }

    @RequestMapping(value = "manager_list_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_list_ajax(Manager manager, Page page) {
        manager.setMerchant(RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, managerService.findManagerList(manager, page), page);
    }

    @RequestMapping(value = "manager_add")
    public ModelAndView manager_add(ModelAndView view) {
        view.setViewName("system/manager_add");
        return view;
    }

    @RequestMapping(value = "manager_add_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_add_ajax(HttpServletRequest request, Manager manager) {
        if (!StringUtil.verifyPassword(manager.getLoginPassword())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "密码不符合规范。");
        }
        if (!CheckUtils.isMobiPhoneNum(manager.getUserPhone())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "手机号不符合规范。");
        }
        if (StringUtils.isNotBlank(manager.getUserEmail()) && !CheckUtils.isEmail(manager.getUserEmail())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "邮箱不符合规范。");
        }
        manager.setLoginPassword(MD5.toMD5(manager.getLoginPassword()));
        manager.setLastLoginTime(new Date());
        manager.setMerchant(RequestThread.get().getMerchant());
        manager.setAccountType(0);
        Manager record = new Manager();
        record.setLoginName(manager.getLoginName());
        record.setMerchant(manager.getMerchant());
        if (managerService.selectCount(record) != 0) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "用户已存在");
        }
        loggerManagerOperation(request, RequestThread.get().getUid(), "管理员新增账户",manager);
        managerService.insertSelective(manager);
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "manager_update")
    public ModelAndView manager_update(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("system/manager_edit");
        return view;
    }

    @RequestMapping(value = "manager_update_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_update_ajax(HttpServletRequest request, Long id, Long roleId, String userName, String userPhone, String userEmail, Integer accountStatus, Integer userSecurity) {
        if (id == null || accountStatus == null) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        if (roleId == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择角色");
        }
        if (StringUtils.isBlank(userName)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请填写用户姓名");
        }
        if (StringUtils.isBlank(userPhone)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请填写用户手机号");
        }
        if (!CheckUtils.isMobiPhoneNum(userPhone)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "手机号不符合规范。");
        }
        if (StringUtils.isNotBlank(userEmail) && !CheckUtils.isEmail(userEmail)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "邮箱不符合规范。");
        }
        Manager manager = managerService.selectByPrimaryKey(id);
        if (manager.getAccountType() == 1) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "不允许修改超级管理员信息");
        }
        if (!manager.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (role == null || !role.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "角色选择错误。");
        }
        loggerManagerOperation(request, RequestThread.get().getUid(), "管理员修改账户",manager);
        manager.setRoleId(roleId);
        manager.setUserName(userName);
        manager.setUserPhone(userPhone);
        manager.setUserEmail(userEmail);
        manager.setAccountStatus(accountStatus);
        manager.setUserSecurity(userSecurity);
        try {
            managerService.updateByPrimaryKeySelective(manager);
            return new ResultMessage(ResponseEnum.M2000);
        } catch (Exception e) {
            logger.error("账户修改失败。error_msg={}", e.getMessage());
        }
        return new ResultMessage(ResponseEnum.M4000);
    }

    @RequestMapping(value = "manager_detail")
    public ModelAndView manager_detail(ModelAndView view) {
        view.setViewName("system/manager_detail");
        return view;
    }

    @RequestMapping(value = "manager_detail_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_detail_ajax(Long id) {
        Manager manager = managerService.selectById(id);
        if (!manager.getMerchant().equalsIgnoreCase(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4004);
        }
        Map<String, Object> data = Maps.newHashMap();
        data.put("manager", manager);
        if (manager.getRoleId() != null) {
        	Role item = new Role();
        	item.setId(manager.getRoleId());
        	item.setMerchant(RequestThread.get().getMerchant());
            Role role = roleMapper.selectOne(item);
            if (role != null) {
                data.put("roleName", role.getRoleName());
            }
        }
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    @RequestMapping(value = "manager_current_detail_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_current_detail_ajax() {
        Manager manager = managerService.selectById(RequestThread.get().getUid());
        Map<String, Object> data = Maps.newHashMap();
        data.put("manager", manager);
        if (manager.getRoleId() != null) {
        	Role item = new Role();
        	item.setId(manager.getRoleId());
        	item.setMerchant(RequestThread.get().getMerchant());
            Role role = roleMapper.selectOne(item);
            if (role != null) {
                data.put("roleName", role.getRoleName());
            }
        }
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    @RequestMapping(value = "manager_update_pwd")
    public ModelAndView manager_update_pwd(ModelAndView view) {
        view.setViewName("system/manager_update_pwd");
        return view;
    }

    @RequestMapping(value = "manager_update_pwd_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_update_pwd_ajax(HttpServletRequest request, String old_pwd, String new_pwd) {
        if (StringUtils.isEmpty(old_pwd)) {
            return new ResultMessage(ResponseEnum.M4001);
        }
        if (StringUtils.isEmpty(new_pwd)) {
            return new ResultMessage(ResponseEnum.M4002);
        }
        if (!StringUtil.verifyPassword(new_pwd)) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "密码不符合规范。");
        }
        Manager manager = managerService.selectByPrimaryKey(RequestThread.get().getUid());
        if (!manager.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        if (!manager.getLoginPassword().equals(MD5.toMD5(old_pwd))) {
            return new ResultMessage(ResponseEnum.M4003);
        }
        try {
        	loggerManagerOperation(request, RequestThread.get().getUid(), "用户修改密码",manager);
            Manager record = new Manager();
            record.setId(manager.getId());
            record.setLoginPassword(MD5.toMD5(new_pwd));
            managerService.updateByPrimaryKeySelective(record);
            redisMapper.remove(RedisConst.USER_TOKEN + RequestThread.get().getUid());
            return new ResultMessage(ResponseEnum.M2000);
        } catch (Exception e) {
            logger.error("账户修改密码失败,error_msg={}", e.getMessage());
        }
        return new ResultMessage(ResponseEnum.M4000);
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "manager_reset_ajax", method = {RequestMethod.POST})
    public ResultMessage manager_reset_ajax(HttpServletRequest request,Long id) {
        Manager manager = managerService.selectByPrimaryKey(id);
        if (!manager.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        loggerManagerOperation(request, RequestThread.get().getUid(), "管理员重置密码",manager);
        String password = RandomUtils.generatePassword(10).toString();
        manager.setLoginPassword(MD5.toMD5(password));
        try {
            managerService.updateByPrimaryKeySelective(manager);
            return new ResultMessage(ResponseEnum.M2000, password);
        } catch (Exception e) {
            logger.error("账户重置密码失败,error_msg={}", e.getMessage());
        }
        return new ResultMessage(ResponseEnum.M4000);
    }

	/**
	 * 操作账号的日志打印
	 * 
	 * @param request
	 */
	private static void loggerManagerOperation(HttpServletRequest request,Long manager_id, String message, Manager manager_target) {
		logger.info("账户信息操作,ip={},manager_id={},type={},target={}",HttpUtils.getIpAddr(request, "."), manager_id,message,JSON.toJSONString(manager_target));
	}
    
}
