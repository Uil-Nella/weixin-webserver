package com.yonyou.weixin.core.oauth2.util;

import org.apache.log4j.Logger;



import net.sf.json.JSONObject;

import com.yonyou.weixin.core.model.AccessToken;
import com.yonyou.weixin.core.model.Result;

/**
 * 微信企业号调用类 {"errcode":0,"errmsg":"ok"} 此结果表示调用方法成功返回
 * 
 * @author Sunlight
 * 
 */
public class WeixinUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinUtil.class);

	private static AccessToken accessToken=null;
	/**
	 * 获取企业号AccessToken
	 * 
	 * @param CorpID
	 * @param CorpSecret
	 * @return
	 */
	public static AccessToken getAccessToken(String CorpID, String CorpSecret) {
		if(null==accessToken){
			accessToken = WechatAccessToken.getAccessToken(CorpID,
					CorpSecret, 1);
		}
		return accessToken;
	}

	/**
	 * OAuth2验证接口根据code获取成员信息
	 * 
	 * @param token
	 * @param code
	 * @param AgentID
	 * @return
	 */
	public static Result<String> oAuth2GetUserByCode(String token, String code,
			int AgentID) {
		Result<String> result = new Result<String>();
		JSONObject jo = WechatOAuth2.getUserByCode(token, code, AgentID);
		if (jo != null) {
			try {
				String userId = jo.getString("UserId");
				if (userId != null && userId.length() > 0) {
					result.setErrmsg("");
					result.setErrcode("0");
					result.setObj(userId);
				} else {
					result.setErrmsg(jo.getString("errmsg"));
					result.setErrcode(jo.getString("errcode"));
				}

			} catch (Exception e) {
				logger.error("oAuth2GetUserByCode(String, String, int)", e);

				result.setErrmsg("accessToken 超时......");
				result.setErrcode("42001");
			}
		}
		return result;
	}
}
