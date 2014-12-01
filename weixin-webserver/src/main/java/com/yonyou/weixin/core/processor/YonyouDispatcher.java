package com.yonyou.weixin.core.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.yonyou.weixin.core.model.TextMessage;
import com.yonyou.weixin.core.service.TextMsgService;
import com.yonyou.weixin.core.tencent.AesException;
import com.yonyou.weixin.core.tencent.WXBizMsgCrypt;
import com.yonyou.weixin.core.util.IMessageType;
import com.yonyou.weixin.core.util.MessageUtil;

public class YonyouDispatcher {
	public static String process(HttpServletRequest request) {
		Map<String, String> requestMap = null;
		String resp = "响应失败！";
		try {
			String data = getRequestData(request);
			requestMap = MessageUtil.parseXml(data);
			String msgType = requestMap.get("MsgType");
			TextMessage textMessage = getResponseMsg(requestMap, msgType);
			String respContent="";
			// 文本消息  
            if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_TEXT)) {  
                String content = requestMap.get("Content");   
//                textMessage.setContent(new TextMsgService().excute(content));
                respContent = "uap提示：您发送的是文本消息！内容是："+content;  
            }  
            // 图片消息  
            else if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "uap提示：您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "uap提示：您发送的是地理位置消息！";   
            }  
            // 链接消息  
            else if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "uap提示：您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "uap提示：您发送的是音频消息！";  
            }  
            textMessage.setContent(respContent);
            
			// 事件推送
			if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				
				textMessage.setContent("您请求的事件msgType是："+msgType+",eventType值是："+eventType+",eventKey是："+eventKey);
				// 订阅
				if (eventType.equals(IMessageType.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent( "欢迎使用UAP消息中心");
				}
				// 取消订阅
				else if (eventType.equals(IMessageType.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(IMessageType.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
//					String eventKey = requestMap.get("EventKey");
					textMessage.setContent( new TextMsgService().excute(eventKey));
					if (eventKey.equals("12")) {
					}
					if (IMessageType.EVENT_TYPE_CLICK.equalsIgnoreCase(eventKey)) {
						textMessage.setContent( new TextMsgService().excute(eventKey));
					}
				}
			}
			resp = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	/**
	 * 预处理拼装message
	 * @param requestMap
	 * @param msgType
	 * @return
	 */
	private static TextMessage getResponseMsg(Map<String, String> requestMap,
			String msgType) {
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		
		System.out.println("FromUserName:"+fromUserName+",toUserName:"+toUserName+",msgType:"+msgType);
		System.out.println("comtent:"+requestMap.get("Content"));
		System.out.println("event:"+requestMap.get("Event")+"，eventkey："+requestMap.get("EventKey"));
		
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(IMessageType.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		return textMessage;
	}

	private static String getRequestData(HttpServletRequest request)
			throws IOException, AesException {
		InputStream inputStream = request.getInputStream();
		String postData=IOUtils.toString(inputStream, "UTF-8");
		// 微信加密签名
		String sVerifyMsgSig = request.getParameter("msg_signature");
		// 时间戳
		String sVerifyTimeStamp = request.getParameter("timestamp");
		// 随机数
		String sVerifyNonce = request.getParameter("nonce");
		
		String data = WXBizMsgCrypt.getDefaultInstance().DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postData);
		return data;
	}
}
