package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.NoticeMapper;
import com.mod.loan.model.Notice;
import com.mod.loan.service.NoticeService;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice, Long> implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Map<String, Object>> findNoticeList(Notice notice, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("noticeTitle", notice.getNoticeTitle());
		param.put("noticeStatus", notice.getNoticeStatus());
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		param.put("merchant",notice.getMerchant());
		page.setTotalCount(noticeMapper.noticeCount(notice));
		return noticeMapper.findNoticeList(param);
	}

}