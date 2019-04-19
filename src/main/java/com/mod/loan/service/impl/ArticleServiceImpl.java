package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.ArticleMapper;
import com.mod.loan.model.Article;
import com.mod.loan.service.ArticleService;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, Long> implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Map<String, Object>> findArticleList(Article article, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("articleTag", article.getArticleTag());
		param.put("articleTitle", article.getArticleTitle());
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		param.put("merchant",article.getMerchant());
		page.setTotalCount(articleMapper.articleCount(article));
		return articleMapper.findArticleList(param);
	}

}
