package com.mod.loan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.util.HttpUtils;

/**
 * 
 * @author wugy 2018年1月9日 下午5:43:36
 */
@ControllerAdvice(annotations = { RestController.class, Controller.class })
//@EnableWebMvc
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultMessage handleException(HttpServletRequest request, Exception e) {
		logger.error("uid={},ip={},url={},error_msg={}", RequestThread.get().getUid(), HttpUtils.getIpAddr(request, "."), request.getRequestURI(), e.getMessage());
		printException(e);
		return new ResultMessage(ResponseEnum.M4000);
	}

	public void printException(Exception e) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			// 将出错的栈信息输出到printWriter中
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		logger.error(new Date() + ":" + sw.toString());
	}

}
