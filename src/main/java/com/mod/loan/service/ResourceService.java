package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.Resource;
import com.mod.loan.model.Role;

/**
 * 
 * @author wgy 2016-5-3上午11:54:45
 *
 */
public interface ResourceService extends BaseService<Resource, Long> {

	/**
	 * 查找菜单
	 * 
	 * @param parentId
	 *            父ID 等于0时为模块菜单
	 * @param status
	 *            状态 null-所有，0-正常，1-停用
	 * @return
	 */
	List<Resource> findResourceList(Long parentId, Integer status);

	/**
	 * 给模块级权限菜单获取所有子菜单
	 * 
	 * @param parentList
	 *            模块级权限
	 * @param status
	 *            获取菜单权限null-所有，0-正常，1-停用
	 * @return
	 */
	boolean findAllResourceList(List<Resource> parentList, Integer status);

	/**
	 * 根据角色和账号类型获取权限菜单的ID Map，管理员获取所有权限
	 * @param accountType 账号类型  1-管理员 2-普通账号
	 * @param roles  角色
	 * @return
	 */
	Map<Long, Long> findRoleResourceList(Integer accountType,Role... roles);

	List<Resource> findByManagerId(Long id);
}
