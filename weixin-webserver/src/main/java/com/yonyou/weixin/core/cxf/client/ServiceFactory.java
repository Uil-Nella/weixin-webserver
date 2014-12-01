package com.yonyou.weixin.core.cxf.client;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * 获取服务的实例工厂 获取的服务是单列的 java 客户端调用云端服务的方式，首先要通过wsdl2java
 * 命令将相关文件准备好，然后本地还需要相关cxf的jar包，
 * 
 * @author lvzh
 *
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
