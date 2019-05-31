package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.UserBank;

public interface UserBankService extends BaseService<UserBank, Long> {

	UserBank selectUserCurrentBankCard(Long uid);

}
