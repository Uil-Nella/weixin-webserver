package com.yonyou.weixin.core.oauth2.util;

import org.apache.log4j.Logger;



import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.yonyou.weixin.core.model.AccessToken;
import com.yonyou.weixin.core.oauth2.enums.EnumMethod;

/**
 * 公众平台通用接口工具类
 * 
 */
public class WechatAccessToken {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(WechatAccessToken.class);

	// 获取微信公众号：access_token的接口地址（GET） 限2000（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取企业号access_token
	public final static String company_access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret, int type) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		if (type == 1) {
			requestUrl = company_access_token_url.replace("CORPID", appid).replace("CORPSECRET", appsecret);
			if (logger.isInfoEnabled()) {
				logger.info("getAccessToken(String, String, int)"+requestUrl);
			}
		}
		JSONObject jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		if(jsonObject==null){
			jsonObject = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		}
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败

				logger.error("getAccessToken(String, String, int)", e);
			}
		}
		return accessToken;
	}

}