package com.yonyou.weixin.core.model;

/**
 * 微信ACCESS_TOKEN 调用API必须参数 可通过APPID和APPSECRET获取 每天2000个限制
 * <p/>
 * <p>
 * @author 刘新宇
 *
 * <p>
 * @date 2014年12月1日 下午6:26:32
 * <p>
 * @version 0.0.1
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
