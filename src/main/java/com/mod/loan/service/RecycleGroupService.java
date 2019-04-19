package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.RecycleGroup;

/**
 * Created by chenanle on 2018/6/28.
 */
public interface RecycleGroupService extends BaseService<RecycleGroup, Long> {

    List<Map<String, Object>> findRecycleGroupList(Map<String, Object> param, Page page);

}
