package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Feedback;

public interface FeedbackMapper extends MyBaseMapper<Feedback> {

	int feedbackCount(Map<String, Object> param);

	List<Map<String, Object>> findFeedbackList(Map<String, Object> param);

}