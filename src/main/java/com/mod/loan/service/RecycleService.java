package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.OrderRecycleRecord;
import com.mod.loan.service.form.OrderQuery;

public interface RecycleService extends BaseService<OrderRecycleRecord, Long> {

    List<Map<String, Object>> findOverdueList(OrderQuery query, Page page);

    List<Map<String, Object>> findAuditOverdueList(Map<String,Object> param);

    List<Map<String, Object>> findRecycleList(Map<String, Object> param);

    List<Map<String, Object>> findRecycleRepayList(Map<String, Object> param);

    List<Map<String, Object>> findAllRecycleList(OrderQuery query, Page page);

    /**
     * 催单excel导出
     */
    int selectOverdueUserMessageCount(OrderQuery query);

    /**
     * 新增催收excel导出记录，发送消息
     *
     * @param query
     */
    void insertExportRecordsAndSendMessage(OrderQuery query);

    List<Map<String, Object>> findDownloadList(Map<String, Object> param);


}
