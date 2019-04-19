package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.HomeMapper;
import com.mod.loan.model.Home;
import com.mod.loan.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenanle on 2018/7/9.
 */
@Service
public class HomeServiceImpl extends BaseServiceImpl<Home, Long> implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<Map<String, Object>> findHomeList(Home home, Page page) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("status",home.getStatus());
        param.put("startIndex",page.getStartIndex());
        param.put("pageSize",page.getPageSize());
        param.put("merchant",home.getMerchant());
        page.setTotalCount(homeMapper.homeCount(param));
        return homeMapper.findHomeList(param);
    }
}
