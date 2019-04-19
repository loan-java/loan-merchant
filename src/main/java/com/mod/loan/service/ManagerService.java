package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Manager;

/**
 * @author wgy 2016-5-3上午11:54:45
 */
public interface ManagerService extends BaseService<Manager, Long> {

    // 多条件查找
    List<Map<String, Object>> findManagerList(Manager manager, Page page);

    boolean verifyIp(Manager manager, String ip);

	Manager selectById(Long id);

}
