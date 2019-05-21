package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Order;
import com.mod.loan.model.User;
import com.mod.loan.model.dto.StrategyDTO;
import org.aspectj.weaver.ast.Or;

public interface OrderService extends BaseService<Order, Long> {

    List<StrategyDTO> findOrderRiskStrategyList(long id);

    void updateOrderFollowUser(Long followUserId, String merchant, Long... ids);

    // 多条件查找
    List<Map<String, Object>> findOrderList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findOrderPassList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findWorkbenchList(Map<String, Object> param, Page page);

    List<Map<String, Object>> exportReport(Map<String, Object> param);

    /**
     * 查找未被取单的订单
     *
     * @param
     * @return
     */
    List<Long> findUnAuditOrder(Map<String, Object> param);

    /**
     * 统计未被取单的订单数
     *
     * @return
     */
    Integer countUnAuditOrder();

    /**
     * 放款
     *
     * @param id
     */
    void orderMakeLoans(Long id, String payType);

    /**
     * 当日统计
     *
     * @param merchant
     * @param searchTime
     * @return
     */
    Map<String, Object> mainData(String merchant, String searchTime);

    /**
     * 取单
     *
     * @param getOrderNumber
     */
    void saveTakeOutOrder(Long getOrderNumber);

    void orderCallBack(User user, Order order);

}
