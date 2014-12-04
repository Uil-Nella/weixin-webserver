package com.yonyou.weixin.core.message.processor;

import org.apache.commons.lang.StringUtils;

import com.yonyou.weixin.core.context.LoginContext;
import com.yonyou.weixin.core.cxf.client.ServiceFactory;
import com.yonyou.weixin.core.cxf.client.YYUserWS;
import com.yonyou.weixin.core.message.model.SendMessage;
import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
/**
 * 消息处理类
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月4日 上午9:52:16
 * <p> @version 0.0.1
 */
public class MsgRuleProcessor {
	public static final String START_COMMOND = "$";
	/**
	 * 修改域账号信息
	 * @param resMsg  $域账号@旧密码@新密码  ， 密码含有“@”或“$” 使用单引号(英文)： $'域账号'@'旧密码'@'新密码'"
	 */
	public static void updatePdomainNameAndPass(String resMsg){
		if(!isMatchRule(resMsg)||!isMatchUpdateRule(resMsg)) {
			SendMessage.postMsg(SendMessage.STextMsgWithoutPartyAndTag(LoginContext.staff.getUserid(), String.valueOf(APPConstants.AGENTID), "输入无效"));
			return ;
		}
		StringUtils.remove(resMsg, "$");
		//FIXME 更新数据库数据
		String username = resMsg.substring(0, "@".indexOf(resMsg));
		StringUtils.remove(resMsg, username);
		StringUtils.remove(resMsg, "@");
		String oldpass = resMsg.substring(0, "@".indexOf(resMsg));
		StringUtils.remove(resMsg, "@");
		StringUtils.remove(resMsg, oldpass);
		String newpass = resMsg;
		//FIXME 校验旧密码的正确性
		YYUserWS service =  ServiceFactory.getServiceInstance();
		service.updateUser(LoginContext.staff.getUserid(), username, newpass);
		SendMessage.postMsg(SendMessage.STextMsgWithoutPartyAndTag(LoginContext.staff.getUserid(), String.valueOf(APPConstants.AGENTID), "修改成功"));
	}
	public static boolean isMatchRule(String resMsg){
		return resMsg==null?false:resMsg.startsWith(START_COMMOND);
	}
	public static boolean isMatchUpdateRule(String resMsg){
		return "@".indexOf(resMsg)!="@".lastIndexOf(resMsg);
	}
}
