package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.QuestionRef;

public interface QuestionRefMapper extends MyBaseMapper<QuestionRef> {

	int questionRefCount(Map<String, Object> param);

	List<Map<String, Object>> findQuestionRefList(Map<String, Object> param);

	Integer selectMaxIdxByTypeId(@Param("typeId") Long typeId);

}