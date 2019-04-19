package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Role;

/**
 * 
 * @author wgy 2016-5-3上午11:54:45
 *
 */
public interface RoleService extends BaseService<Role, Long> {

	// 多条件查找角色
	List<Role> findRoleList(Role role, Page page);

	// 根据状态查找角色
	List<Role> findAllRoleList(Integer roleStatus);

	/**
	 * 根据role id删除role以及role关联的权限。
	 * 
	 * @param id
	 * @return 总影响行数。
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 角色权限更新
	 * 
	 * @param map
	 * @param ids
	 */
	void updateRoleResource(Long roleId, Map<Long, Long> roleResourceMap, Long[] ids);
}
