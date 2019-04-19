package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.QuestionRef;

public interface QuestionRefService extends BaseService<QuestionRef, Integer> {

	// 多条件查找
	List<Map<String, Object>> findQuestionRefList(QuestionRef questionRef, Page page);

	Integer selectMaxIdxByTypeId(Long typeId);

}
