package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Order;

public interface OrderMapper extends MyBaseMapper<Order> {

	void updateOrderFollowUser(@Param("followUserId") Long followUserId, @Param("merchant") String merchant, @Param("ids") Long... ids);

	/**
	 * 用户提单情况
	 * @param merchant
	 * @param id
	 * @return
	 */
	Map<String, Object> countUserOrderRecord(@Param("merchant") String merchant, @Param("uid") Long id);

	List<Map<String, Object>> findUserByOrderId(Map<String, Object> param);

	/**
	 * 共债记录
	 *
	 * @param userPhone
	 * @return
	 */
	Map<String, Object> countDebtRecord(@Param("userPhone") String userPhone);

	int orderCount(Map<String, Object> param);

//	int orderPassCount(Map<String, Object> param);

	List<Map<String, Object>> findOrderList(Map<String, Object> param);

	List<Map<String, Object>> findOrderPassList(Map<String, Object> param);

	int WorkbenchCount(Map<String, Object> param);

	List<Map<String, Object>> findWorkbenchList(Map<String, Object> param);

	List<Long> findUnAuditOrder(Map<String, Object> param);

	Integer countUnAuditOrder(Map<String, Object> param);

	/**
	 * 根据商户与日期统计当日订单情况
	 * 
	 * @return
	 */
	Map<String, Object> countOrderMessageByDay(@Param("merchant") String merchant, @Param("searchTime") String searchTime);

	List<Map<String, Object>> exportReport(Map<String, Object> param);

	Order selectOrderById(@Param("id") Long id);

}