package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Blacklist;

public interface BlacklistMapper extends MyBaseMapper<Blacklist> {

    List<Map<String, Object>> findBlacklistList(Map<String, Object> param);

    int blacklistCount(Map<String, Object> param);

    int findByUid(Long uid);

    Blacklist findObjectByUid(Long uid);

}