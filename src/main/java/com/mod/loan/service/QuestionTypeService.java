package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.QuestionType;

public interface QuestionTypeService extends BaseService<QuestionType, Long> {

	// 多条件查找
	List<Map<String, Object>> findQuestionTypeList(Map<String, Object> param, Page page);

}
