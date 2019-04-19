package com.mod.loan.config.redis;

/**
 * Created by lijy on 2017/7/19.
 */
public interface RedisConst {
	String REDIS_LOCK = "redis:lock:";
	String TAKEOUT_LOCK = "takeout:lock:"; // 取单时的商户锁
	String USER_TOKEN = "user:token:";
	String USER_RIGHT = "user:right:";
	String MAIN_STATISTICS = "main:statistics:";
	String LOGIN_ERROR_FLAG = "login:error:flag:";
	String CURRENT_ORIGIN_TWO_DAYS = "current:origin:two:days:"; // 近两天的实时渠道数据
	String ORIGIN_ORDER_STATISTICS = "origin:order:statistics:";// 渠道订单统计
	String RECYCLE_REPAY_STATISTICS = "recycle:repay:statistics:";// 催收还款统计
	String AUDIT_OVERDUE_STATISTICS = "audit:overdue:statistics:";// 信审逾期统计
	String ORDER_PASS_STATISTICS = "order:pass:statistics:";// 信审逾期统计
	String SINGLE_DEVICE_LOGIN_FLAG = "single:device:login:flag:";// 单设备登录次数记录
	String LOCK_ILLEGAL_IP = "lock:illegal:ip:";// 非法ip
	String USER_SECURITY_CODE = "user:security:code:";// ip验证未通过后的验证码
	String USER_SECURITY_CODE_SECOND = "user:security:code:second:";// ip验证未通过后的验证码
}
