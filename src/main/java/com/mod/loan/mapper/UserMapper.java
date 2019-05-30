package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends MyBaseMapper<User> {

	int userCount(Map<String, Object> param);

	List<Map<String, Object>> findUserList(Map<String, Object> param);

	int countRegisterUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	int countRealNameUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	int countAlipayUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	int countMobileUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	int countBindbankUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	int countUserDetailsUserNumberToDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	/**
	 * 用户渠道列表导出
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportUserOriginReport(Map<String, Object> param);

	@Select("select count(*) from tb_user where merchant =#{merchant} and user_origin=#{userOrigin} ")
	int countAllUser(@Param(value = "merchant") String merchant, @Param(value = "userOrigin") String userOrigin);

	@Select(" select count(uid) from tb_user_ident ui left join tb_user u on u.id = ui.uid where u.merchant = #{merchant} and bindbank != 0 ")
	int countBindbankUserNumberAll(@Param(value = "merchant") String merchant);

}