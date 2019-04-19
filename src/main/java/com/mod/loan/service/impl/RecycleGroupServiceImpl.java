package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.RecycleGroupMapper;
import com.mod.loan.model.RecycleGroup;
import com.mod.loan.service.RecycleGroupService;

/**
 * Created by chenanle on 2018/6/28.
 */
@Service
public class RecycleGroupServiceImpl extends BaseServiceImpl<RecycleGroup, Long> implements RecycleGroupService {

    @Autowired
    private RecycleGroupMapper recycleGroupMapper;

    @Override
    public List<Map<String, Object>> findRecycleGroupList(Map<String, Object> param, Page page) {
        param.put("startIndex",page.getStartIndex());
        param.put("pageSize",page.getPageSize());
        page.setTotalCount(recycleGroupMapper.recycleGroupCount(param));
        return recycleGroupMapper.findRecycleGroupList(param);
    }
}
