package com.mod.loan.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Resource;
import com.mod.loan.model.Role;
import com.mod.loan.service.ResourceService;
import com.mod.loan.service.RoleService;
import com.mod.loan.util.ArrayUtil;
import com.mod.loan.util.ResourceUtil;

@RestController
@RequestMapping(value = "system")
public class RoleController {
	public static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "role_list")
	public ModelAndView role_list(ModelAndView view) {
		view.setViewName("system/role_list");
		return view;
	}

	@RequestMapping(value = "role_all_list", method = { RequestMethod.POST })
	public ResultMessage role_all_list() {
		Role record = new Role();
		record.setRoleStatus(0);
		record.setMerchant(RequestThread.get().getMerchant());
		List<Role> roleList = roleService.select(record);
		return new ResultMessage(ResponseEnum.M2000, roleList);
	}

	@RequestMapping(value = "role_list_ajax", method = { RequestMethod.POST })
	public ResultMessage role_list_ajax(Role role, Page page) {
		role.setMerchant(RequestThread.get().getMerchant());
		List<Role> roleList = roleService.findRoleList(role, page);
		return new ResultMessage(ResponseEnum.M2000, roleList, page);
	}

	@RequestMapping(value = "role_add")
	public ModelAndView role_add(ModelAndView view) {
		view.setViewName("system/role_add");
		return view;
	}

	@RequestMapping(value = "role_add_ajax", method = { RequestMethod.POST })
	public ResultMessage role_add_ajax(Role role) {
		role.setMerchant(RequestThread.get().getMerchant());
		roleService.insertSelective(role);
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "role_update")
	public ModelAndView role_update(ModelAndView view, Long id) {
		view.addObject("id", id);
		view.setViewName("system/role_edit");
		return view;
	}

	@RequestMapping(value = "role_update_ajax", method = { RequestMethod.POST })
	public ResultMessage role_update_ajax(Role role) {
		if (!RequestThread.get().getMerchant().equals(roleService.selectByPrimaryKey(role.getId()).getMerchant())) {
			return new ResultMessage(ResponseEnum.M4000);
		}
		roleService.updateByPrimaryKeySelective(role);
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "role_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage role_detail_ajax(Long id) {
		Map<String, Object> data = Maps.newHashMap();
		Role role = roleService.selectByPrimaryKey(id);
		data.put("role", role);
		return new ResultMessage(ResponseEnum.M2000, data);
	}

	// 角色权限编辑
	@RequestMapping(value = "role_resource_edit")
	public ModelAndView role_resource_edit(ModelAndView view, Long id) {
		view.addObject("roleId", id);
		view.setViewName("system/role_resource_edit");
		return view;
	}

	/**
	 * 获取角色权限关系
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "role_resource_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage role_resource_edit_ajax(Long roleId) {
		Map<String, Object> data = new HashMap<>();
		Role role = roleService.selectByPrimaryKey(roleId);
		Map<Long, Long> map = resourceService.findRoleResourceList(0, role);
		List<Resource> parentResourceList = resourceService.findResourceList(0L, 0);
		resourceService.findAllResourceList(parentResourceList, 0);
		ResourceUtil.markResource(parentResourceList, map);
		String list = JSONObject.toJSONString(parentResourceList);
		data.put("zTreeNodes", list.replaceAll("resourceName", "name").replaceAll("childResource", "nodes").replaceAll("hasResource", "checked"));
		return new ResultMessage(ResponseEnum.M2000, data);
	}

	/**
	 * 更新角色权限关系
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "role_resource_update_ajax", method = { RequestMethod.POST })
	public ResultMessage role_resource_update_ajax(Long roleId, String resourceId) {
		Role role = roleService.selectByPrimaryKey(roleId);
		if (!RequestThread.get().getMerchant().equals(role.getMerchant())) {
			return new ResultMessage(ResponseEnum.M4000);
		}
		Map<Long, Long> roleResourceMap = resourceService.findRoleResourceList(0, role);
		String idStrings = resourceId;
		Long[] ids = null;
		if (idStrings == null) {
			System.out.println("ids不能为空");
		} else if (idStrings.trim().equals("")) {
			ids = new Long[0];
		} else {
			ids = ArrayUtil.toLongArray(idStrings, ",");
		}
		roleService.updateRoleResource(role.getId(), roleResourceMap, ids);
		return new ResultMessage(ResponseEnum.M2000);
	}
}
