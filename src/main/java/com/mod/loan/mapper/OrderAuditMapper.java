package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderAudit;

public interface OrderAuditMapper extends MyBaseMapper<OrderAudit> {

    int orderAuditCount(Map<String, Object> param);

    List<Map<String, Object>> findOrderAuditList(Map<String, Object> param);

    List<Map<String, Object>> findAuditListByUid(@Param("uid") Long uid, @Param("merchant") String merchant);

    Map<String, Object> countAuditOrderMessage(Map<String, Object> param);

    List<Map<String, Object>> findOrderAuditNameList(Map<String, Object> param);

}