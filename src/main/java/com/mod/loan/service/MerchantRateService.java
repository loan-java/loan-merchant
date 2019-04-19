package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.MerchantRate;

public interface MerchantRateService extends BaseService<MerchantRate, Long> {

	List<Map<String, Object>> findMerchantRateList(MerchantRate merchantRate, Page page);

	int selectByBorrowType(Integer borrowType);

}
