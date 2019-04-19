package com.mod.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.MerchantMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.service.MerchantService;

@Service
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, String> implements MerchantService {

	@Autowired
	private RedisMapper redisMapper;
	@Autowired
	private MerchantMapper merchantMapper;

	@Override
	public Merchant findMerchantByAlias(String merchantAlias) {
		Merchant merchant = redisMapper.get(merchantAlias, new TypeReference<Merchant>() {
		});
		if (merchant == null) {
			merchant = merchantMapper.selectByPrimaryKey(merchantAlias);
			if (merchant != null) {
				redisMapper.set(merchantAlias, merchant, 600);
			}
		}
		return merchant;
	}

}
