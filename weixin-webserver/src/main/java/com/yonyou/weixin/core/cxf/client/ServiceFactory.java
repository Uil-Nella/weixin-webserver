package com.yonyou.weixin.core.cxf.client;

import java.net.URL;

import javax.xml.namespace.QName;

/**
 * 获取服务的实例工厂 获取的服务是单列的 java 客户端调用云端服务的方式，首先要通过wsdl2java
 * 命令将相关文件准备好，然后本地还需要相关cxf的jar包，
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月5日 上午9:34:38
 * <p> @version 0.0.1
 */
public class ServiceFactory {
	/**
	 * 客户端调用的实例对象 通过wsdl生成的接口对象
	 */
	private static final QName SERVICE_NAME = new QName(
			"http://service.yyuser.cxf.note.dd.org/", "YYUserWSService");


	/**
	 * 提供给外部使用的获取服务获取实例的方法
	 * 
	 * @return
	 */
	public synchronized static YYUserWS getServiceInstance() {
		URL wsdlURL = YYUserWSService.WSDL_LOCATION;
		
		 YYUserWSService ss = new YYUserWSService(wsdlURL, SERVICE_NAME);
	     YYUserWS port = ss.getYYUserWSPort();
	     return port;
	}
}
