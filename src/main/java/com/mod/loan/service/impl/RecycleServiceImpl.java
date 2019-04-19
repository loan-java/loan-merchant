package com.mod.loan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.OrderRecycleRecordMapper;
import com.mod.loan.mapper.RecycleOrderExportMapper;
import com.mod.loan.model.OrderRecycleRecord;
import com.mod.loan.model.RecycleOrderExport;
import com.mod.loan.service.RecycleService;
import com.mod.loan.service.form.OrderQuery;

@Service
public class RecycleServiceImpl extends BaseServiceImpl<OrderRecycleRecord, Long> implements RecycleService {

	@Autowired
    private RedisMapper redisMapper;
    @Autowired
    private RecycleOrderExportMapper recycleOrderExportMapper;
    @Autowired
    private OrderRecycleRecordMapper recycleMapper;

    @Override
    public List<Map<String, Object>> findOverdueList(OrderQuery query, Page page) {
        query.setStartIndex(page.getStartIndex());
        query.setPageSize(page.getPageSize());
        int totalCount = recycleMapper.countOverdueList(query);
        page.setTotalCount(totalCount);
        return totalCount > 0 ? recycleMapper.findOverdueList(query) : new ArrayList<Map<String, Object>>();
    }

    @Override
    public List<Map<String, Object>> findAuditOverdueList(Map<String,Object> param) {
    	String key = "%s:%s:%s:%s";
		key = String.format(key, param.get("merchant"), param.get("userType"), param.get("startTime"), param.get("endTime"));
		List<Map<String, Object>> data = redisMapper.get(RedisConst.AUDIT_OVERDUE_STATISTICS + key, new TypeReference<List<Map<String, Object>>>() {
		});
		if (data == null) {
			data = recycleMapper.findAuditOverdueList(param);
			redisMapper.set(RedisConst.AUDIT_OVERDUE_STATISTICS + key, data, 180);
		}
		return data;
    }

    @Override
    public List<Map<String, Object>> findRecycleRepayList(Map<String,Object> param) {
    	String key = "%s:%s:%s:%s";
		key = String.format(key, param.get("merchant"), param.get("userType"), param.get("startTime"), param.get("endTime"));
		List<Map<String, Object>> data = redisMapper.get(RedisConst.RECYCLE_REPAY_STATISTICS + key, new TypeReference<List<Map<String, Object>>>() {
		});
		if (data == null) {
			data = recycleMapper.findRecycleRepayList(param);
			redisMapper.set(RedisConst.RECYCLE_REPAY_STATISTICS + key, data, 180);
		}
		return data;
    }

    @Override
    public List<Map<String, Object>> findRecycleList(Map<String, Object> param) {
        return recycleMapper.findRecycleList(param);
    }

    @Override
    public List<Map<String, Object>> findAllRecycleList(OrderQuery query, Page page) {
        query.setStartIndex(page.getStartIndex());
        query.setPageSize(page.getPageSize());
        page.setTotalCount(recycleMapper.countAuditList(query));
        return recycleMapper.findAllRecycleList(query);
    }

    @Override
    public int selectOverdueUserMessageCount(OrderQuery query) {
        return recycleMapper.selectOverdueUserMessageCount(query);
    }

    @Override
    public void insertExportRecordsAndSendMessage(OrderQuery query) {
        //新增催收订单导出记录
        RecycleOrderExport recycleOrderExport = new RecycleOrderExport();
        recycleOrderExport.setMerchant(RequestThread.get().getMerchant());
        recycleOrderExport.setManagerId(RequestThread.get().getUid());
        recycleOrderExport.setStatus(0);// 状态：正在进行
        recycleOrderExport.setParam(JSONObject.toJSONString(query));
        recycleOrderExport.setCreateTime(new Date());
        recycleOrderExport.setUpdateTime(new Date());
        recycleOrderExportMapper.insertSelective(recycleOrderExport);
    }

    @Override
    public List<Map<String, Object>> findDownloadList(Map<String, Object> param) {
        return recycleOrderExportMapper.findDownloadList(param);
    }

}
