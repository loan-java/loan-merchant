package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.RecycleOrderExport;

import java.util.List;
import java.util.Map;

public interface RecycleOrderExportMapper extends MyBaseMapper<RecycleOrderExport> {

    List<Map<String, Object>> findDownloadList(Map<String, Object> param);
}