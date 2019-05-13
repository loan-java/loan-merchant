package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.Merchant;

public interface MerchantService extends BaseService<Merchant,String> {

	Merchant findMerchantByAlias(String merchantAlias);

	/**
	 * 查询商户账户余额，单位 分
	 * @param merchantAlias
	 * @return
	 */
	long findMerchantBalanceFen(String merchantAlias);
}
