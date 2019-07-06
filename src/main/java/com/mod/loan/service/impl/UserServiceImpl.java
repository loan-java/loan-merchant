package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.User;
import com.mod.loan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> findUserList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(userMapper.userCount(param));
        List<Map<String, Object>> list = userMapper.findUserList(param);
        return list;
    }

    @Override
    public List<Map<String, Object>> exportUserOriginReport(Map<String, Object> param) {
        return userMapper.exportUserOriginReport(param);
    }

}