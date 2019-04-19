package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.MerchantOrigin;

public interface OriginService extends BaseService<MerchantOrigin, Long> {

    List<MerchantOrigin> findOriginListByManagerId(Long managerId);

    void updateManagerOrigin(Long managerId, List<Long> ids);

    List<Map<String, Object>> findOriginRegisterByManagerId(Long originId, Long managerId, String startTime, String endTime, Page page);

    List<Map<String, Object>> findOriginList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findCurrentOriginStatisticsList(Map<String, Object> param, Page page);

    List<Map<String, Object>> findOriginStatistics(Map<String, Object> param);

    List<Map<String, Object>> findOriginOrderList(Map<String, Object> param);


}
