package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Article;

public interface ArticleMapper extends MyBaseMapper<Article> {
	
	int articleCount(Article article);

	// 多条件查询
	List<Map<String, Object>> findArticleList(Map<String, Object> param);
	
}