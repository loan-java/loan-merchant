package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Notice;

public interface NoticeMapper extends MyBaseMapper<Notice> {

	// 多条件查找
	List<Map<String, Object>> findNoticeList(Map<String, Object> param);

	int noticeCount(Notice notice);

}