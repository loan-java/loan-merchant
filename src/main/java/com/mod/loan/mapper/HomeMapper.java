package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Home;

import java.util.List;
import java.util.Map;

public interface HomeMapper extends MyBaseMapper<Home> {

    int homeCount(Map<String, Object> param);

    List<Map<String,Object>> findHomeList(Map<String,Object> param);
}