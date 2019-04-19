package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.RecycleUser;
import com.mod.loan.model.dto.RecycleUserDto;

/**
 * Created by chenanle on 2018/6/28.
 */
public interface RecycleUserService extends BaseService<RecycleUser, Long> {

    List<Map<String, Object>> findRecycleUserList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findRealRecycleUserList(String merchant);

    List<Map<String, Object>> findFenPeiRecycleUserList(Map<String, Object> param);

    RecycleUserDto findRecycleUserDetail(RecycleUserDto recycleUserDto);

    List<Map<String, Object>> findRecycleLoginRecord(Map<String, Object> param);


}
