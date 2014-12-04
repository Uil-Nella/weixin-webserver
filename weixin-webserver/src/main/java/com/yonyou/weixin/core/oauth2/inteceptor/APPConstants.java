package com.yonyou.weixin.core.oauth2.inteceptor;

/**
 * 常量说明： 此处定义的常量需要持久化，可以保存在数据库中，在需要的地方读取。 在多企业号中，最好以每个应用来定义。 此处为UAP消息中心 常量
 * <p/>
 * <p>
 * 
 * @author 刘新宇
 *
 *         <p>
 * @date 2014年12月1日 下午6:33:11
 *       <p>
 * @version 0.0.1
 */
public interface APPConstants {
	/**
	 * 应用ID
	 */
	public static final int AGENTID = 1;
	/**
	 * TOKEN
	 */
	public static final String TOKEN = "xQtfQTeNBkcZI2uOjoftgyOROR1x";
	/**
	 * 企业ID
	 */
	public static final String CORPID = "wx569905b8c1a2a573";
	/**
	 * 企业密钥
	 */
	public static final String APPSECRET = "30uvar_b1vXFYAd8HE1H4kdNGG5aAmzAfqodjVK4sedxfMRSfIce_rVgwYD3UTIM";
	/**
	 * 加密字符串
	 */
	public static final String encodingAESKey = "rjhGuYgsW7hc2AGI6wQoc8umXEL9zbrd7B6bVCXDes5";
	/**
	 * 企业部署WEBAPP名称 后续会修改
	 */
	public static final String APP_NAME = "weixin-web";
	/**
	 * 应用编码字符集
	 */
	public static final String APP_ENCODING = "utf-8";
	/**
	 * 当前登录的员工
	 */
	public static final String CURRENT_LOGIN_STAFF = "current_staff";

}