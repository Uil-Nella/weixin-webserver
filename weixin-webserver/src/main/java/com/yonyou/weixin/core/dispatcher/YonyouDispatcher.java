package com.yonyou.weixin.core.dispatcher;

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
/**
 * 微信请求分发器
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 上午11:26:23
 * <p> @version 0.0.1
 */
public class YonyouDispatcher {
	public static final String MSGTYPE = "MsgType";
	public static final String MSG_FAILURE = "响应失败";
	public static final String EVENT = "Event";
	public static final String EVENT_KEY = "EventKey";
	public static final  String CONTENT = "Content";
	public static final String FROM_USERNAME = "FromUserName";
	public static final String TO_USERNAME = "ToUserName";
	public static final String MSG_SIGNTURE = "msg_signature";
	public static final String TIMESTAMP = "timestamp";
	public static final String NONCE = "nonce";

	public static String process(HttpServletRequest request) {
		Map<String, String> requestMap = null;
		String resp = MSG_FAILURE;
		try {
			String data = getRequestData(request);
			requestMap = MessageUtil.parseXml(data);
			//请求的类型
			String msgType = requestMap.get(MSGTYPE);
			//返会给请求端消息
			TextMessage textMessage = getResponseMsg(requestMap, msgType);
			String respContent="";
			//处理消息
			analysisMsg(requestMap, msgType, textMessage, respContent);
            //处理事件
			analysisEvent(requestMap, msgType, textMessage);
			resp = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	/**
	 * 处理事件
	 * @param requestMap
	 * @param msgType
	 * @param textMessage
	 */
	private static void analysisEvent(Map<String, String> requestMap,
			String msgType, TextMessage textMessage) {
		// 事件推送
		if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			
			String eventType = requestMap.get(EVENT);
			String eventKey = requestMap.get(EVENT_KEY);
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
				// String eventKey = requestMap.get("EventKey");
				textMessage.setContent(new TextMsgService().excute(eventKey));
				if (IMessageType.EVENT_TYPE_CLICK.equalsIgnoreCase(eventKey)) {
					textMessage.setContent(new TextMsgService()
							.excute(eventKey));
				}
			}
		}
	}
	/**
	 * 处理消息
	 * @param requestMap
	 * @param msgType
	 * @param textMessage
	 * @param respContent
	 */
	private static void analysisMsg(Map<String, String> requestMap,
			String msgType, TextMessage textMessage, String respContent) {
		// 文本消息  
		if (msgType.equals(IMessageType.REQ_MESSAGE_TYPE_TEXT)) {  
			String content = requestMap.get(CONTENT);   
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
		String fromUserName = requestMap.get(FROM_USERNAME);
		// 公众帐号
		String toUserName = requestMap.get(TO_USERNAME);
		
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(IMessageType.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		return textMessage;
	}
	/**
	 * 获取请求中的签名及时间戳等
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws AesException
	 */
	private static String getRequestData(HttpServletRequest request)
			throws IOException, AesException {
		InputStream inputStream = request.getInputStream();
		String postData=IOUtils.toString(inputStream, "UTF-8");
		// 微信加密签名
		String sVerifyMsgSig = request.getParameter(MSG_SIGNTURE);
		// 时间戳
		String sVerifyTimeStamp = request.getParameter(TIMESTAMP);
		// 随机数
		String sVerifyNonce = request.getParameter(NONCE);
		
		String data = WXBizMsgCrypt.getDefaultInstance().DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postData);
		return data;
	}
}
