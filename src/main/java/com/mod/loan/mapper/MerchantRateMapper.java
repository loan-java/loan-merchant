package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MerchantRate;

public interface MerchantRateMapper extends MyBaseMapper<MerchantRate> {

	int merchantRateCount(Map<String, Object> param);

	List<Map<String, Object>> findMerchantRateList(Map<String, Object> param);

	int selectByBorrowType(@Param("borrowType") Integer borrowType, @Param("merchant") String merchant);

	MerchantRate findByMerchant(@Param("merchant") String merchant);
}