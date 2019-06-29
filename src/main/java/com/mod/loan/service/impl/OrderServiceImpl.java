package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.enums.*;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.*;
import com.mod.loan.model.Manager;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.model.User;
import com.mod.loan.model.dto.StrategyDTO;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.MerchantService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.ConstantUtils;
import com.mod.loan.util.MoneyUtil;
import com.mod.loan.util.juhe.CallBackJuHeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    public static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private UserSmsMapper userSmsMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private OrderAuditMapper orderAuditMapper;
    @Autowired
    OrderRecycleLogMapper orderRecycleLogMapper;
    @Autowired
    private MerchantRateMapper merchantRateMapper;
    @Resource
    private MerchantService merchantService;
    @Autowired
    private OrderRepayMapper orderRepayMapper;

    @Autowired
    private AccountRechargeMapper accountRechargeMapper;

    @Autowired
    private CallBackRongZeService callBackRongZeService;

    @Override
    public void updateOrderFollowUser(Long followUserId, String merchant, Long... ids) {
        if (ids == null | ids.length == 0) {
            throw new RuntimeException("ids is null or length is 0");
        }
        orderMapper.updateOrderFollowUser(followUserId, merchant, ids);
        orderRecycleLogMapper.insertOrderRecycleLog(followUserId, merchant, ids);
    }

    @Override
    public List<Map<String, Object>> findOrderList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderMapper.orderCount(param));
        String searchType = String.valueOf(param.get("searchType"));
        List<Map<String, Object>> list = orderMapper.findOrderList(param);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(map -> {
                map.put("contract_url", Constant.sysDomainHost + "/static/loan-contract.html?uid=" + map.get("uid") + "&orderNo=" + map.get("order_no") + "&source=" + OrderSourceEnum.RONGZE.getSoruce());
//                String str = (String) map.get("strategies");
//                if (StringUtils.isBlank(str)) return;
//
//                List<StrategyDTO> strategyList;
//
//                if (StringUtils.startsWith(str, "[{")) {
//                    strategyList = JSON.parseArray(str, StrategyDTO.class);
//                } else {
//                    strategyList = bindStrategyDTOList(str);
//                }
//
//                map.put("strategies", null);
//                map.put("strategyList", strategyList);
                //获取每个订单的代扣结果-由菜单决定
                //searchType: 1-线下还款
                if (searchType != null && searchType.equals("1")) {
                    Integer status = orderRepayMapper.getLastRepayStatus(String.valueOf(map.get("id")));
                    //0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
                    map.put("repayStatus", status);
                } else {
                    map.put("repayStatus", null);
                }
            });
        }
        return list;
    }

    @Override
    public List<StrategyDTO> findOrderRiskStrategyList(long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);

        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(1);
        List<Map<String, Object>> list = findOrderList(param, page);
        if (CollectionUtils.isNotEmpty(list)) {
            Object strategies = list.get(0).get("strategyList");

            List<StrategyDTO> strategyList = (List<StrategyDTO>) strategies;
            return strategyList != null ? strategyList : null;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> findOrderPassList(Map<String, Object> param, Page page) {
        String key = "%s:%s:%s:%s";
        key = String.format(key, param.get("merchant"), param.get("userType"), param.get("startTime"), param.get("endTime"));
        List<Map<String, Object>> data = redisMapper.get(RedisConst.ORDER_PASS_STATISTICS + key, new TypeReference<List<Map<String, Object>>>() {
        });
        if (data == null) {
            data = orderMapper.findOrderPassList(param);
            redisMapper.set(RedisConst.ORDER_PASS_STATISTICS + key, data, 180);
        }
        return data;
    }

    @Override
    public List<Map<String, Object>> findWorkbenchList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(orderMapper.WorkbenchCount(param));
        return orderMapper.findWorkbenchList(param);
    }

    @Override
    public List<Map<String, Object>> exportReport(Map<String, Object> param) {
        List<Map<String, Object>> orderList = orderMapper.exportReport(param);
        for (Map<String, Object> map : orderList) {
            if (map.get("user_type").equals(1)) {
                map.put("user_type", "新客");
            }
            if (map.get("user_type").equals(2)) {
                map.put("user_type", "次新");
            }
            if (map.get("user_type").equals(3)) {
                map.put("user_type", "老客");
            }
            if (map.get("status").equals(11)) {
                map.put("status", "机审中");
            }
            if (map.get("status").equals(12)) {
                map.put("status", "等待复审");
            }
            if (map.get("status").equals(21)) {
                map.put("status", "待放款");
            }
            if (map.get("status").equals(22)) {
                map.put("status", "放款中");
            }
            if (map.get("status").equals(23)) {
                map.put("status", "放款失败");
            }
            if (map.get("status").equals(31)) {
                map.put("status", "还款中");
            }
            if (map.get("status").equals(32)) {
                map.put("status", "还款确认中");
            }
            if (map.get("status").equals(33)) {
                map.put("status", "逾期");
            }
            if (map.get("status").equals(34)) {
                map.put("status", "坏账");
            }
            if (map.get("status").equals(41)) {
                map.put("status", "已结清");
            }
            if (map.get("status").equals(42)) {
                map.put("status", "逾期还款");
            }
            if (map.get("status").equals(51)) {
                map.put("status", "自动审核失败");
            }
            if (map.get("status").equals(52)) {
                map.put("status", "复审失败");
            }
            if (map.get("status").equals(53)) {
                map.put("status", "取消");
            }
        }
        return orderList;
    }

    @Override
    public List<Long> findUnAuditOrder(Map<String, Object> param) {
        return orderMapper.findUnAuditOrder(param);
    }

    @Override
    public Integer countUnAuditOrder() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("status", Constant.ORDER_FOR_AUDITING);
        return orderMapper.countUnAuditOrder(param);
    }

    @Override
    public void orderMakeLoans(Long id, String payType) {
        Order order = orderMapper.selectOrderById(id);
        if (order.getMerchant().equals(RequestThread.get().getMerchant())) {
            if (order.getStatus() == Constant.ORDER_FOR_LENDING || order.getStatus() == Constant.ORDER_LEND_FAIL) {
                // 修改订单状态
                order.setStatus(Constant.ORDER_IN_LENDING);
                orderMapper.updateByPrimaryKey(order);
                if (order.getSource() == ConstantUtils.ONE) {
                    callBackRongZeService.pushOrderStatus(order);
                } else {
                    orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), orderMapper.selectByPrimaryKey(id));
                }
                // 发送消息
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderId", id);
                jsonObject.put("payType", payType);
                System.out.println("放款类型：" + order.getPaymentType());
                System.out.println("==========================================");
                if (PaymentTypeEnum.BAOFOO.getCode().equals(order.getPaymentType())) {
                    rabbitTemplate.convertAndSend(RabbitConst.baofoo_queue_order_pay, jsonObject);
                } else if (PaymentTypeEnum.KUAIQIAN.getCode().equals(order.getPaymentType())) {
                    rabbitTemplate.convertAndSend(RabbitConst.kuaiqian_queue_order_pay, jsonObject);
                }
                if (PaymentTypeEnum.CHANPAY.getCode().equals(order.getPaymentType())) {
                    rabbitTemplate.convertAndSend(RabbitConst.chanpay_queue_order_pay, jsonObject);
                } else if (PaymentTypeEnum.YEEPAY.getCode().equals(order.getPaymentType())) {
                    rabbitTemplate.convertAndSend(RabbitConst.yeepay_queue_order_pay, jsonObject);
                }
            }
        }
    }

    @Override
    public Map<String, Object> mainData(String merchant, String searchTime) {
        Map<String, Object> data = redisMapper.get(RedisConst.MAIN_STATISTICS + merchant + searchTime, new TypeReference<Map<String, Object>>() {
        });
        if (data == null) {
            data = new HashMap<>();
            data.put("merchantRate", merchantRateMapper.findByMerchant(RequestThread.get().getMerchant()));
            data.put("countRegisterUserNumberToDay", userMapper.countRegisterUserNumberToDay(merchant, searchTime));
            data.put("countRealNameUserNumberToDay", userMapper.countRealNameUserNumberToDay(merchant, searchTime));
            data.put("countUserDetailsUserNumberToDay", userMapper.countUserDetailsUserNumberToDay(merchant, searchTime));
            data.put("countMobileUserNumberToDay", userMapper.countMobileUserNumberToDay(merchant, searchTime));
            data.put("countBindbankUserNumberToDay", userMapper.countBindbankUserNumberToDay(merchant, searchTime));
//            data.put("countOverdueAmount", orderMapper.countOverdueAmount(merchant));
            data.put("otherFee", orderMapper.otherFee(merchant));
            data.putAll(orderMapper.countOrderMessageByDay(merchant, searchTime));
            data.put("balance", Double.valueOf(MoneyUtil.fen2YuanStr(merchantService.findMerchantBalanceFen(merchant))));
            data.put("countFlowAmount", orderMapper.countFlowAmount());//风控订单个数
            /**
             * hsd=流量费+风控费+金融挂靠费+银行卡权鉴+代付费+支付费+短信验证码费用
             * 聚合流量费=32*完整注册
             * 融泽流量费=1509*30%*30%*放款人数
             *
             * 风控=10*申请人数
             * 权鉴费=0.5*绑卡人数
             *
             * 金融挂靠费=放款金额*0.3%
             * 代付费=1*代付笔数（成功笔数）
             * 支付费=支付金额*0.33%（协议支付成功笔数）
             * 短信验证码费=下发条数*0.1
             *
             * jsd=流量费+风控费+短信费用
             */


            //金融挂靠费
            double countLoanAmountAll = Double.valueOf(data.get("countLoanAmountAll").toString());
            double jrgkf = countLoanAmountAll * 0.003;

            //绑卡权鉴费
            Integer countBindbankUserNumber = userMapper.countBindbankUserNumberAll(merchant);
            double jqf = countBindbankUserNumber * 0.5;

            //绑卡代付费
            double countBackNumberAll = Double.valueOf(data.get("countBackNumberAll").toString());
            double dff = countBackNumberAll * 1;

            //支付费
            double sucessOrderForPaymentType = orderMapper.sucessOrderForPaymentType(merchant, "baofoo");
            double zff = sucessOrderForPaymentType * 0.0033;

            //风控个数
            Integer countFlowAmount = (int) data.get("countFlowAmount");

            //风控费
            double fkf = 0d;
            //流量费
            double lif = 0d;
            //短信费用
            double dxf = 0d;

            //算融泽 1509*30%*30%*成功放款笔数
            int successOrderRZ = orderMapper.sucessOrder(merchant, OrderSourceEnum.RONGZE.getSoruce());

            //算聚合 32*完整注册
            //int countAllUser = userMapper.countAllUser(merchant, UserOriginEnum.JH.getCode());
            //lif += 32 * countAllUser;

            if (MerchantEnum.isXiaoHuQianBao(merchant)) {
                lif = successOrderRZ * 1509 * 0.3 * 0.27;
                fkf = countFlowAmount * 5.5;
                dxf = userSmsMapper.countUserSms() * 0.1;
                double sum = lif + fkf + dxf;
                data.put("merchantBalance", accountRechargeMapper.getAccountRecharge());
            } else if (MerchantEnum.isHuaShiDai(merchant)) {
                lif = successOrderRZ * 1509 * 0.3 * 0.3;
                fkf = countFlowAmount * 5.5;
                dxf = userSmsMapper.countUserSms() * 0.1;
                double sum = lif + fkf + dxf;
                data.put("merchantBalance", accountRechargeMapper.getAccountRecharge()-sum);
            }

            redisMapper.set(RedisConst.MAIN_STATISTICS + merchant + searchTime, data, 900);
        }
        return data;
    }

    @Override
    public void saveTakeOutOrder(Long getOrderNumber) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("status", Constant.ORDER_FOR_AUDITING);
        param.put("getOrderNumber", getOrderNumber != null ? getOrderNumber : 10);
        List<Long> orderIds = orderMapper.findUnAuditOrder(param);
        // 插入审核记录
        OrderAudit orderAudit = null;
        Manager manager = managerMapper.selectByPrimaryKey(RequestThread.get().getUid());
        for (Long id : orderIds) {
            orderAudit = new OrderAudit();
            orderAudit.setOrderId(id);
            orderAudit.setAuditId(manager.getId());
            orderAudit.setAuditName(manager.getUserName());
            orderAudit.setStatus(2); // 审核中
            orderAudit.setCreteTime(new Date());
            orderAudit.setMerchant(RequestThread.get().getMerchant());
            orderAuditMapper.insertSelective(orderAudit);
        }

    }

    public void orderCallBack(User user, Order order) {

        JSONObject object = JSONObject.parseObject(user.getCommonInfo());
        object.put("orderNo", order.getOrderNo());
        object.put("orderType", OrderTypeEnum.JK.getCode());
        object.put("shouldRepayAmount", new BigDecimal(order.getShouldRepay().toString()).stripTrailingZeros().toPlainString());
        object.put("accountId", order.getUid());
        switch (order.getStatus()) {
            case 21:
                object.put("orderStatus", OrderStatusEnum.WAIT_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 22:
                object.put("orderStatus", OrderStatusEnum.TO_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 23:
                object.put("orderStatus", OrderStatusEnum.PAY_FAILED.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 31:
                object.put("orderStatus", OrderStatusEnum.PAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYING.getCode());
                break;
            case 33:
                object.put("orderStatus", OrderStatusEnum.OVERDUE.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 34:
                object.put("orderStatus", OrderStatusEnum.BAD_DEBT.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 41:
                object.put("orderStatus", OrderStatusEnum.REPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYED.getCode());
                break;
            case 42:
                object.put("orderStatus", OrderStatusEnum.OVERDUEREPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.OVERDUE_REPAY.getCode());
                break;
            case 51:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 52:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 53:
                object.put("orderStatus", OrderStatusEnum.CANCEL.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            default:
                break;
        }
        CallBackJuHeUtil.callBack(Constant.juheCallBackUrl, object);
    }

    private List<StrategyDTO> bindStrategyDTOList(String strategy) {

        try {
            strategy = strategy.substring(1);
            strategy = strategy.substring(0, strategy.length() - 1);

            List<String> list = Arrays.asList(strategy.split("StrategyDTO\\("));

            List<StrategyDTO> dtoList = new ArrayList<>();

            list.forEach(str -> {
                str = replace(str);

                if (StringUtils.isBlank(str)) return;

                String[] arr = str.trim().split("ruleResList=");
                if (arr.length == 0) return;

                StrategyDTO strategyDTO = new StrategyDTO();

                List<String> strategyList = Arrays.asList(arr[0].trim().split(","));
                String code = null, desc = null, score = null, index = null;

                if (CollectionUtils.isNotEmpty(strategyList))
                    for (String st : strategyList) {
                        if (StringUtils.isBlank(st)) return;
                        st = st.trim();
                        if (st.contains("code")) {
                            code = subValue(st);
                        } else if (st.contains("desc")) {
                            desc = subValue(st);
                        } else if (st.contains("score")) {
                            score = subValue(st);
                        } else if (st.contains("index")) {
                            index = subValue(st);
                        }
                    }

                strategyDTO.setCode(StringUtils.equals(code, "null") ? null : code);
                strategyDTO.setDesc(StringUtils.equals(desc, "null") ? null : desc);
                strategyDTO.setScore(StringUtils.equals(score, "null") ? null : score);
                strategyDTO.setIndex(StringUtils.equals(index, "null") ? null : index);

                if (arr.length > 1) {
                    String ss = arr[1];
                    ss = ss.substring(1);
                    ss = ss.substring(0, ss.length() - 1);
                    List<String> r = Arrays.asList(ss.trim().split("RuleResDTO\\("));

                    List<StrategyDTO.RuleResDTO> ruleList = new ArrayList<>();

                    for (String rr : r) {

                        rr = replace(rr);
                        if (StringUtils.isBlank(rr)) continue;

                        StrategyDTO.RuleResDTO rule = new StrategyDTO.RuleResDTO();

                        List<String> ruleS = Arrays.asList(rr.trim().split(","));

                        String code2 = null, desc2 = null, score2 = null, labelValue = null, isDefaultVal = null, ruleName = null, ruleId = null;

                        if (CollectionUtils.isNotEmpty(ruleS))
                            for (String rr2 : ruleS) {
                                rr2 = replace(rr2);
                                if (StringUtils.isBlank(rr2)) continue;

                                if (rr2.contains("code")) {
                                    code2 = subValue(rr2);
                                } else if (rr2.contains("desc")) {
                                    desc2 = subValue(rr2);
                                } else if (rr2.contains("score")) {
                                    score2 = subValue(rr2);
                                } else if (rr2.contains("labelValue")) {
                                    labelValue = subValue(rr2);
                                } else if (rr2.contains("isDefaultVal")) {
                                    isDefaultVal = subValue(rr2);
                                } else if (rr2.contains("rule_name")) {
                                    ruleName = subValue(rr2);
                                } else if (rr2.contains("rule_id")) {
                                    ruleId = subValue(rr2);
                                }
                            }

                        rule.setCode(StringUtils.equals(code2, "null") ? null : code2);
                        rule.setDesc(StringUtils.equals(desc2, "null") ? null : desc2);
                        rule.setScore(StringUtils.equals(score2, "null") ? null : score2);
                        rule.setLabelValue(StringUtils.equals(labelValue, "null") ? null : Boolean.valueOf(labelValue));
                        rule.setIsDefaultVal(StringUtils.equals(isDefaultVal, "null") ? null : isDefaultVal);
                        rule.setRule_name(StringUtils.equals(ruleName, "null") ? null : ruleName);
                        if (StringUtils.isNotBlank(ruleId) && !StringUtils.equals(ruleId, "null"))
                            rule.setRule_id(ruleId.replaceAll("\\)", "").replaceAll("]", ""));
                        ruleList.add(rule);
                    }

                    strategyDTO.setRuleResList(ruleList);
                }
                dtoList.add(strategyDTO);
            });
            return dtoList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private String replace(String str) {
        if (StringUtils.isBlank(str)) return "";

        String v = str;
        if (str.endsWith("), ")) v = v.substring(0, v.lastIndexOf("), "));

        return v.trim();
    }

    private String subValue(String str) {
        if (!StringUtils.contains(str, "=")) return "";

        return str.substring(str.indexOf("=") + 1).trim();
    }

    public static void main(String[] args) {

        User user = new User();
        user.setCommonInfo("{\"timeStamp\":1558258582,\"accountId\":\"\",\"sign\":\"8B46CB7F8A962E5E4DD71669978DB357\",\"mobile\":\"15980981733\",\"intefaceType\":\"WCWOISnFvBAWDBA\",\"deviceCode\":\"00000000-6c31-b0d7-ffff-ffff89974fe1\",\"terminalId\":\"A\",\"source\":\"20190325158\",\"version\":\"3.1.3.2\",\"token\":\"\"}");
        user.setId(1358L);
        Order order = new Order();
        order.setStatus(31);
        order.setOrderNo("b20190518135840479485659");
        order.setShouldRepay(new BigDecimal("1207.50"));
        order.setUid(1358L);
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderCallBack(user, order);
    }


}
