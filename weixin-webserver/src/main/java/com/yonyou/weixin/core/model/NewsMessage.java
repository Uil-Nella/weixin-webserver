package com.yonyou.weixin.core.model;

import java.util.List;

/**
 * 图文消息
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 下午6:28:41
 * <p> @version 0.0.1
 */
public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}