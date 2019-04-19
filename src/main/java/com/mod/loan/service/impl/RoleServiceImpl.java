package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.RoleMapper;
import com.mod.loan.mapper.RoleResourceMapper;
import com.mod.loan.model.Role;
import com.mod.loan.model.RoleResource;
import com.mod.loan.service.RoleService;

/**
 * 
 * @author wgy
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public List<Role> findRoleList(Role role, Page page) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merchant", role.getMerchant());
		params.put("roleStatus", role.getRoleStatus());
		params.put("startIndex", page.getStartIndex());
		params.put("pageSize", page.getPageSize());
		page.setTotalCount(roleMapper.selectCount(role));
		return roleMapper.findRoleList(params);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		int roleNum = roleMapper.deleteByPrimaryKey(id);
		RoleResource roleResource = new RoleResource();
		roleResource.setRoleId(id);
		int roleResourceNum = roleResourceMapper.delete(roleResource);
		return roleNum + roleResourceNum;
	}

	@Override
	public List<Role> findAllRoleList(Integer roleStatus) {
		if (roleStatus == null) {
			return roleMapper.selectAll();
		} else {
			Role role = new Role();
			role.setRoleStatus(roleStatus);
			return roleMapper.select(role);
		}

	}

	@Override
	public void updateRoleResource(Long roleId, Map<Long, Long> map, Long[] ids) {
		for (Long resourceId : ids) {
			Long old_resourceId = map.remove(resourceId);
			if (old_resourceId == null) {
				RoleResource   new_resource= new RoleResource(roleId, resourceId);
				roleResourceMapper.insert(new_resource);
			}
		}
		for (Long removeResource : map.keySet()) {
			RoleResource remove=new RoleResource();
			remove.setResourceId(removeResource);
			remove.setRoleId(roleId);
			roleResourceMapper.delete(remove);
		}
	}
}
