package com.yonyou.weixin.core.oauth2.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器（用于HTTPS请求）
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 下午6:47:19
 * <p> @version 0.0.1
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}