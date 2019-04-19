package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.ManagerMapper;
import com.mod.loan.mapper.ResourceMapper;
import com.mod.loan.mapper.RoleResourceMapper;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Resource;
import com.mod.loan.model.Role;
import com.mod.loan.model.RoleResource;
import com.mod.loan.service.ResourceService;
import com.mod.loan.util.ResourceUtil;

/**
 * 
 * @author wgy
 *
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Autowired
	RedisMapper redisMapper;
	@Override
	public boolean findAllResourceList(List<Resource> parentList, Integer status) {
		Resource r = null;
		for (Resource resource : parentList) {
			r = new Resource();
			r.setPid(resource.getId());
			r.setResourceStatus(status);
			resource.setChildResource(resourceMapper.findResourceList(r));
		}
		return true;
	}

	@Override
	public Map<Long, Long> findRoleResourceList(Integer accountType, Role... roles) {
		Map<Long, Long> resourceMap = new HashMap<Long, Long>();
		if (accountType == 1) {
			Resource record = new Resource();
			record.setResourceStatus(0);
			List<Resource> resources = resourceMapper.select(record);
			for (Resource resource : resources) {
				resourceMap.put(resource.getId(), resource.getId());
			}
		} else {
			for (Role role : roles) {
				RoleResource _roleResource = new RoleResource();
				_roleResource.setRoleId(role.getId());
				List<RoleResource> roleResources = roleResourceMapper.select(_roleResource);
				for (RoleResource roleResource : roleResources) {
					resourceMap.put(roleResource.getResourceId(), roleResource.getResourceId());
				}
			}
		}
		return resourceMap;
	}


	@Override
	public List<Resource> findResourceList(Long parentId, Integer status) {
		Resource resource = new Resource();
		resource.setPid(parentId);
		resource.setResourceStatus(status);
		return resourceMapper.findResourceList(resource);
	}

	@Override
	public List<Resource> findByManagerId(Long id) {
		List<Resource> list = redisMapper.get(RedisConst.USER_RIGHT+id, new TypeReference<List<Resource>>() {
		});
		if (list==null) {
			Manager manager = managerMapper.selectByPrimaryKey(id);
			Role role=new Role();
			role.setId(manager.getRoleId());
			Map<Long, Long> map = findRoleResourceList(manager.getAccountType(),role);
			List<Resource> parentResourceList =findResourceList(0L, 0);
			findAllResourceList(parentResourceList, 0);
			list = ResourceUtil.getResource(parentResourceList, map);
			redisMapper.set(RedisConst.USER_RIGHT+id, list, 30);
		}
		return list;
	}

}
