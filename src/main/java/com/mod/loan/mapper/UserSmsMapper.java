package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserSms;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserSmsMapper extends MyBaseMapper<UserSms> {

    @Select("select count(*) from tb_user_sms  ")
    Integer countUserSms();
}