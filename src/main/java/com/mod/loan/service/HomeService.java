package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Home;

import java.util.List;
import java.util.Map;

/**
 * Created by chenanle on 2018/7/9.
 */
public interface HomeService extends BaseService<Home, Long> {

    List<Map<String, Object>> findHomeList(Home home, Page page);
}
