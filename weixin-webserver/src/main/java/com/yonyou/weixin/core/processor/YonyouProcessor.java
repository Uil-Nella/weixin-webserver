package com.yonyou.weixin.core.processor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import com.yonyou.weixin.core.model.TextMessage;
import com.yonyou.weixin.core.service.TextMsgService;
import com.yonyou.weixin.core.util.IMessageType;
import com.yonyou.weixin.core.util.MessageUtil;

public class YonyouProcessor {
	public static String process(HttpServletRequest request) {
		Map<String, String> requestMap = null;
		String resp = "响应失败！";
		try {
			// xml请求解析
			requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			
			// 回复文本消息
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(IMessageType.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setFuncFlag(0);
			// 文本消息
			if (IMessageType.REQ_MESSAGE_TYPE_TEXT.equalsIgnoreCase(msgType)) {
				String content = requestMap.get("Content").trim();
				resp = new TextMsgService().excute(content);
			}
			// 事件推送
			else if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(IMessageType.EVENT_TYPE_SUBSCRIBE)) {
					resp = "欢迎使用UAP消息中心";
				}
				// 取消订阅
				else if (eventType.equals(IMessageType.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(IMessageType.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					resp = new TextMsgService().excute(eventKey);
					if (eventKey.equals("12")) {
					}
					if (IMessageType.EVENT_TYPE_CLICK.equalsIgnoreCase(msgType)) {
						resp = new TextMsgService().excute(toUserName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
