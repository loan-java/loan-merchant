package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.FeedbackMapper;
import com.mod.loan.model.Feedback;
import com.mod.loan.service.FeedbackService;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Long> implements FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public List<Map<String, Object>> findFeedbackList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(feedbackMapper.feedbackCount(param));
		return feedbackMapper.findFeedbackList(param);
	}

}