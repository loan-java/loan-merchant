package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.AccountRecharge;
import org.apache.ibatis.annotations.Select;

/**
 * loan-merchant 2019/6/29 huijin.shuailijie Init
 */
public interface AccountRechargeMapper extends MyBaseMapper<AccountRecharge> {

    @Select("select ifnull(sum(recharge_money),0) from tb_account_recharge")
    double getAccountRecharge();
}
