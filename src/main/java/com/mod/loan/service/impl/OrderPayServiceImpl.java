package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.OrderPayMapper;
import com.mod.loan.model.OrderPay;
import com.mod.loan.service.OrderPayService;

@Service
public class OrderPayServiceImpl extends BaseServiceImpl<OrderPay, Long> implements OrderPayService {

    @Autowired
    private OrderPayMapper orderPayMapper;

    @Override
    public List<Map<String, Object>> findOrderPayList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderPayMapper.orderPayCount(param));
        return orderPayMapper.findOrderPayList(param);
    }

    @Override
    public List<Map<String, Object>> exportReport(Map<String, Object> param) {
        List<Map<String, Object>> list = orderPayMapper.exportReport(param);
        for (Map<String, Object> map : list) {
            if (map.get("pay_status").equals(0)) {
                map.put("pay_status", "初始状态");
            }
            if (map.get("pay_status").equals(1)) {
                map.put("pay_status", "受理成功");
            }
            if (map.get("pay_status").equals(2)) {
                map.put("pay_status", "受理失败");
            }
            if (map.get("pay_status").equals(3)) {
                map.put("pay_status", "放款成功");
            }
            if (map.get("pay_status").equals(4)) {
                map.put("pay_status", "放款失败");
            }
            if (map.get("pay_type").equals(1)) {
                map.put("pay_type", "快钱");
            }
            if (map.get("pay_type").equals(2)) {
                map.put("pay_type", "宝付");
            }
        }
        return list;
    }
}
