package com.mod.loan.controller.system;

import com.mod.loan.common.enums.SmsTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.model.Manager;
import com.mod.loan.model.sms.SmsMessage;
import com.mod.loan.service.ManagerService;
import com.mod.loan.util.CheckUtils;
import com.mod.loan.util.RandomUtils;

@RestController
@RequestMapping(value = "system")
public class SecurityController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RedisMapper redisMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping(value = "security_first_verify", method = { RequestMethod.POST })
	public ResultMessage security_first_verify() {
		if (redisMapper.get(RedisConst.USER_SECURITY_CODE_SECOND + RequestThread.get().getUid()) == null) {
			return new ResultMessage(ResponseEnum.M4000);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}
	
	@RequestMapping(value = "security_code_send_ajax", method = { RequestMethod.POST })
	public ResultMessage security_code_send_ajax() {
		Manager manager = managerService.selectByPrimaryKey(RequestThread.get().getUid());
		if (!CheckUtils.isMobiPhoneNum(manager.getUserPhone())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请正确填写当前账号的手机号码");
		}
		// 发送验证码，5分钟内有效
		String randomNum = RandomUtils.generateRandomNum(6);
		redisMapper.set(RedisConst.USER_SECURITY_CODE_SECOND + manager.getUserPhone(), randomNum, 300);
		logger.info("merchant={},uid={},安全验证未通过,向手机={}发送短信验证码={}", manager.getMerchant(), manager.getId(), manager.getUserPhone(), randomNum);
		rabbitTemplate.convertAndSend(RabbitConst.QUEUE_SMS, new SmsMessage(manager.getMerchant(), SmsTemplate.T002.getKey(), manager.getUserPhone(), randomNum + "|5分钟"));
		return new ResultMessage(ResponseEnum.M2000);
	}
	
	@RequestMapping(value = "security_code_verify_ajax", method = { RequestMethod.POST })
	public ResultMessage security_code_verify_ajax(String code) {
		if (StringUtils.isBlank(code)) {
			return new ResultMessage(ResponseEnum.M4005);
		}
		Manager manager = managerService.selectByPrimaryKey(RequestThread.get().getUid());
		if (!CheckUtils.isMobiPhoneNum(manager.getUserPhone())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请正确填写当前账号的手机号码");
		}
		long increment = redisMapper.increment(RedisConst.USER_SECURITY_CODE_SECOND + manager.getId() + "code_num", 1L, 3600L);
		if (increment > 10) {
			logger.error("一小时内验证码输入次数过多。merchant={},login_name={}", manager.getMerchant(), manager.getLoginName());
			return new ResultMessage(ResponseEnum.M4005);
		}
		if (!code.equals(redisMapper.get(RedisConst.USER_SECURITY_CODE_SECOND + manager.getUserPhone()))) {
			return new ResultMessage(ResponseEnum.M4005);
		}
		redisMapper.set(RedisConst.USER_SECURITY_CODE_SECOND + manager.getId(), true, 3600);
		return new ResultMessage(ResponseEnum.M2000);
	}
}
