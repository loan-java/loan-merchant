package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.OrderRepayMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.service.OrderRepayService;

@Service
public class OrderRepayServiceImpl extends BaseServiceImpl<OrderRepay, Long> implements OrderRepayService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepayMapper orderRepayMapper;

    @Override
    public List<Map<String, Object>> findOrderRepayList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderRepayMapper.orderRepayCount(param));
        return orderRepayMapper.findOrderRepayList(param);
    }

    @Override
    public void updateOrderOffline(Order order, OrderRepay orderRepay) {
        orderMapper.updateByPrimaryKeySelective(order);
        orderRepayMapper.insertSelective(orderRepay);
    }

    @Override
    public List<Map<String, Object>> exportReport(Map<String, Object> param) {
        List<Map<String, Object>> list = orderRepayMapper.exportReport(param);
        for (Map<String, Object> map : list) {
            if (map.get("repay_status").equals(0)) {
                map.put("repay_status", "初始状态");
            }
            if (map.get("repay_status").equals(1)) {
                map.put("repay_status", "受理成功");
            }
            if (map.get("repay_status").equals(2)) {
                map.put("repay_status", "受理失败");
            }
            if (map.get("repay_status").equals(3)) {
                map.put("repay_status", "还款成功");
            }
            if (map.get("repay_status").equals(4)) {
                map.put("repay_status", "还款失败");
            }
            if (map.get("repay_status").equals(5)) {
                map.put("repay_status", "回调信息异常");
            }
            if (map.get("repay_type").equals(1)) {
                map.put("repay_type", "银行卡");
            }
            if (map.get("repay_type").equals(2)) {
                map.put("repay_type", "支付宝");
            }
            if (map.get("repay_type").equals(3)) {
                map.put("repay_type", "微信");
            }
            if (map.get("repay_type").equals(4)) {
                map.put("repay_type", "线下转账");
            }
            if (map.get("repay_type").equals(5)) {
                map.put("repay_type", "富友银行卡");
            }
            if (map.get("repay_type").equals(6)) {
                map.put("repay_type", "汇聚银行卡");
            }
        }
        return list;
    }
}
