package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.User;

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

}