package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderUserMapper extends MyBaseMapper<OrderUser> {

    @Select("select id,order_no as orderNo,source,uid,create_time as createTime " +
            " from tb_user_order where source=#{source} and uid=#{uid} order by id desc limit 1 ")
    OrderUser getUidLastOrder(@Param("source") Integer source, @Param("uid") Long uid);

}