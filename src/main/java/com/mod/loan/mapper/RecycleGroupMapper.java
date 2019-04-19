package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.RecycleGroup;

public interface RecycleGroupMapper extends MyBaseMapper<RecycleGroup> {

    List<Map<String, Object>> findRecycleGroupList(Map<String, Object> param);

    int recycleGroupCount(Map<String, Object> param);


}