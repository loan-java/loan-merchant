package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.OrderAudit;

import java.util.List;
import java.util.Map;

public interface OrderAuditService extends BaseService<OrderAudit, Long> {

    // 多条件查找
    List<Map<String, Object>> findOrderAuditList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findOrderAuditNameList(Map<String, Object> param);

    /**
     * 人工复审
     *
     * @param request
     * @param orderId
     * @param orderAudit
     */
    boolean saveAuditResult(String merchant, OrderAudit orderAudit);

    /**
     * 信审工作台上信审信息统计
     *
     * @return
     */
    Map<String, Object> countAuditOrderMessage();

    int refuseAuditResult(OrderAudit orderAudit);

}
