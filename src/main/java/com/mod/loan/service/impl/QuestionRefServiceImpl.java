package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.QuestionRefMapper;
import com.mod.loan.model.QuestionRef;
import com.mod.loan.service.QuestionRefService;

@Service
public class QuestionRefServiceImpl extends BaseServiceImpl<QuestionRef, Integer> implements QuestionRefService {

	@Autowired
	private QuestionRefMapper questionRefMapper;

	@Override
	public List<Map<String, Object>> findQuestionRefList(QuestionRef questionRef, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeId", questionRef.getTypeId());
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(questionRefMapper.questionRefCount(param));
		return questionRefMapper.findQuestionRefList(param);
	}

	@Override
	public Integer selectMaxIdxByTypeId(Long typeId) {
		return questionRefMapper.selectMaxIdxByTypeId(typeId);
	}

}