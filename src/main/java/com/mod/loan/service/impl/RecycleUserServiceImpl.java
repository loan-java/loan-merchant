package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.RecycleUserMapper;
import com.mod.loan.model.RecycleUser;
import com.mod.loan.model.dto.RecycleUserDto;
import com.mod.loan.service.RecycleUserService;

/**
 * Created by chenanle on 2018/6/28.
 */
@Service
public class RecycleUserServiceImpl extends BaseServiceImpl<RecycleUser, Long> implements RecycleUserService {

    @Autowired
    private RecycleUserMapper recycleUserMapper;


    @Override
    public List<Map<String, Object>> findRecycleUserList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(recycleUserMapper.recycleUserCount(param));
        return recycleUserMapper.findRecycleUserList(param);
    }

    @Override
    public RecycleUserDto findRecycleUserDetail(RecycleUserDto recycleUserDto) {
        RecycleUserDto record = recycleUserMapper.findRecycleUserDetail(recycleUserDto);
        return record;
    }

    @Override
    public List<Map<String, Object>> findRecycleLoginRecord(Map<String, Object> param) {
        return recycleUserMapper.findRecycleLoginRecord(param);
    }

    @Override
    public List<Map<String, Object>> findRealRecycleUserList(String merchant) {
        return recycleUserMapper.findRealRecycleUserList(merchant);
    }

    @Override
    public List<Map<String, Object>> findFenPeiRecycleUserList(Map<String, Object> param) {
        return recycleUserMapper.findFenPeiRecycleUserList(param);
    }

}
