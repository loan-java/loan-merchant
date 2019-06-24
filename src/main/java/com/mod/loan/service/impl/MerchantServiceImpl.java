package com.mod.loan.service.impl;

import com.mod.loan.common.enums.MerchantEnum;
import com.mod.loan.config.Constant;
import com.mod.loan.service.third.baofoo.BaofooBalanceQueryService;
import com.mod.loan.service.third.kuaiqian.KuaiQianBalanceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.MerchantMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.service.MerchantService;

import javax.annotation.Resource;

@Service
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, String> implements MerchantService {

    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    @Resource
    private BaofooBalanceQueryService baofooBalanceQueryService;
    @Resource
    private KuaiQianBalanceQueryService kuaiQianBalanceQueryService;

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

    @Override
    public long findMerchantBalanceFen(String merchantAlias) {
        if (MerchantEnum.isHuaShiDai(merchantAlias)) return baofooBalanceQueryService.queryBalanceFen();

        if (MerchantEnum.isXiaoHuQianBao(merchantAlias)) return kuaiQianBalanceQueryService.queryBalanceFen();

//        if ("dev".equalsIgnoreCase(Constant.env)) return baofooBalanceQueryService.queryBalanceFen();

        return 0L;
    }
}
