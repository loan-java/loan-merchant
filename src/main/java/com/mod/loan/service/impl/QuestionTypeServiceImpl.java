package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.QuestionTypeMapper;
import com.mod.loan.model.QuestionType;
import com.mod.loan.service.QuestionTypeService;

@Service
public class QuestionTypeServiceImpl extends BaseServiceImpl<QuestionType, Long> implements QuestionTypeService {

	@Autowired
	private QuestionTypeMapper quetionTypeMapper;

	@Override
	public List<Map<String, Object>> findQuestionTypeList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(quetionTypeMapper.quetionTypeCount(param));
		return quetionTypeMapper.findQuestionTypeList(param);
	}

}