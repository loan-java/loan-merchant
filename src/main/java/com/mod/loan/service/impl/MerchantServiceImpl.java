package com.mod.loan.service.impl;

import com.mod.loan.service.third.baofoo.BaofooBalanceQueryService;
import com.mod.loan.service.third.kuaiqian.KuaiQianBalanceQueryService;
import com.mod.loan.util.chanpay.ChanpayApiRequest;
import com.mod.loan.util.yeepay.YeePayApiRequest;
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
    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private BaofooBalanceQueryService baofooBalanceQueryService;
    @Resource
    private KuaiQianBalanceQueryService kuaiQianBalanceQueryService;
    @Resource
    private ChanpayApiRequest chanpayApiRequest;

    @Resource
    private YeePayApiRequest yeePayApiRequest;

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
        Merchant merchant = findMerchantByAlias(merchantAlias);
        long balance = 0L;
        switch (merchant.getBindType()) {
            case 4:
                balance = baofooBalanceQueryService.queryBalanceFen();
                break;
            case 5:
                balance = kuaiQianBalanceQueryService.queryBalanceFen();
                break;
            case 6:
                balance = chanpayApiRequest.queryPayBalanceFen();
                break;
            case 7:
                balance = yeePayApiRequest.queryBalanceFen();
                break;
            default:
        }
        return balance;
    }
}
