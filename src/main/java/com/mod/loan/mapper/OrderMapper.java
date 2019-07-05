package com.mod.loan.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Order;
import org.apache.ibatis.annotations.Select;

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

	int countFlowAmount();

	int otherFee(@Param("merchant") String merchant);

	BigDecimal countOverdueAmount(@Param("merchant") String merchant);

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

	@Select("select count(*) from tb_order where merchant = #{merchant} and status in(31,32,33,34,41,42)")
	int sucessOrderAll(@Param(value = "merchant") String merchant);

	@Select("select count(*) from tb_order where merchant = #{merchant} and status in(31,32,33,34,41,42) and source=#{source}")
	int sucessOrder(@Param(value = "merchant") String merchant, @Param(value = "source") Integer source);

	@Select("select count(*) from tb_order where merchant = #{merchant} and source=#{source}")
	int allOrder(@Param(value = "merchant") String merchant, @Param(value = "source") Integer source);

	@Select("select ifnull(sum(a.repay_money),0) from tb_order_repay a left join tb_order b on a.order_id = b.id where b.merchant = #{merchant} " +
			"and a.repay_status in (3) and a.repay_type in (1,7) and b.payment_type=#{paymentType}")
	double sucessOrderForPaymentType(@Param(value = "merchant") String merchant, @Param(value = "paymentType") String paymentType);


	Map<String, Object> countRiskResultForOrder(@Param("id") String id);

	int countFlowAmountToDay(@Param("searchTime") String searchTime);
	int countFilterToDay(@Param("searchTime") String searchTime);

}