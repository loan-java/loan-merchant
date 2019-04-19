package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.RecycleUser;
import com.mod.loan.model.dto.RecycleUserDto;

public interface RecycleUserMapper extends MyBaseMapper<RecycleUser> {

    List<Map<String, Object>> findRecycleUserList(Map<String, Object> param);

    RecycleUserDto findRecycleUserDetail(RecycleUserDto recycleUserDto);

    int recycleUserCount(Map<String, Object> param);

    List<Map<String, Object>> findRealRecycleUserList(String merchant);

    List<Map<String, Object>> findFenPeiRecycleUserList(Map<String, Object> param);

    List<Map<String, Object>> findRecycleLoginRecord(Map<String, Object> param);

}