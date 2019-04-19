package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Article;

public interface ArticleService extends BaseService<Article, Long> {

	// 多条件查找
	List<Map<String, Object>> findArticleList(Article article, Page page);

}
