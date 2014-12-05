package com.yonyou.weixin.core.oauth2.util;

import org.apache.log4j.Logger;


import net.sf.json.JSONObject;

import com.yonyou.weixin.core.oauth2.enums.EnumMethod;

public class WechatOAuth2 {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WechatOAuth2.class);

	private static final String get_oauth2_url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID";

	/**
	 * 根据code获取成员信息
	 * 
	 * @param token
	 * @param code
	 * @param AgentID
	 * @return
	 */
	public static JSONObject getUserByCode(String token, String code, int AgentID) {
		String menuUrl = get_oauth2_url.replace("ACCESS_TOKEN", token).replace("CODE", code).replace("AGENTID", AgentID + "");
		JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByCode(String, String, int)"+jo);
		}

		return jo;
	}

}
