package com.mod.loan.controller.order;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.Order;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "order")
public class OperateController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private CallBackRongZeService callBackRongZeService;

    @RequestMapping(value = "order_bad")
    public ResultMessage order_bad(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null || !order.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "非法操作");
        }
        if (order.getStatus() != 33) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "逾期订单才能设为坏账");
        }
        Order record = new Order();
        record.setId(orderId);
        record.setStatus(34);
        orderMapper.updateByPrimaryKeySelective(record);
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "order_reduce")
    public ResultMessage order_reduce(Long orderId, BigDecimal money) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (money == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请输入减免金额");
        }
        if (order == null || !order.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "非法操作");
        }
        if (order.getStatus() < 30 || order.getStatus() > 40) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "放款中以及逾期订单才能减免金额");
        }
        if (money.signum() < 0) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "减免金额不应小于0");
        }
        if (order.getShouldRepay().compareTo(money) <= 0) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "减免金额应小于应还金额");
        }
        Order record = new Order();
        record.setId(orderId);
        record.setReduceMoney(money);
        record.setShouldRepay(order.getInterestFee().add(order.getBorrowMoney()).add(order.getOverdueFee()).subtract(money));
        orderMapper.updateByPrimaryKeySelective(record);
        if (order.getSource() == ConstantUtils.ONE) {
            callBackRongZeService.pushRepayPlan(orderMapper.selectByPrimaryKey(orderId));
        } else {
            orderService.orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), orderMapper.selectByPrimaryKey(orderId));
        }
        return new ResultMessage(ResponseEnum.M2000);
    }
}
