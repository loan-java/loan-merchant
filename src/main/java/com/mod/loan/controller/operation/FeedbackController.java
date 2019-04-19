package com.mod.loan.controller.operation;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Feedback;
import com.mod.loan.service.FeedbackService;

@RestController
@RequestMapping(value = "operation")
public class FeedbackController {
	public static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "feedback_list")
	public ModelAndView feedback_list(ModelAndView view, Long uid) {
		view.addObject("uid", uid);
		view.setViewName("operation/feedback_list");
		return view;
	}

	@RequestMapping(value = "feedback_list_ajax", method = { RequestMethod.POST })
	public ResultMessage feedback_list_ajax(Feedback feedback, Page page, String startTime, String endTime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", feedback.getUid());
		param.put("merchant", RequestThread.get().getMerchant());
		param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
		param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
		return new ResultMessage(ResponseEnum.M2000, feedbackService.findFeedbackList(param, page), page);
	}

	@RequestMapping(value = "feedback_detail")
	public ModelAndView feedback_detail(ModelAndView view, Long id) {
		view.addObject("id", id);
		view.setViewName("operation/feedback_detail");
		return view;
	}

	@RequestMapping(value = "feedback_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage feedback_detail_ajax(Feedback feedback) {
		feedback.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, feedbackService.selectOne(feedback));
	}

}
