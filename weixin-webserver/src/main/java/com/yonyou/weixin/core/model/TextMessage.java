package com.yonyou.weixin.core.model;

/**
 * 文本消息
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 下午6:28:56
 * <p> @version 0.0.1
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
