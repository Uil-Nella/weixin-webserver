package com.yonyou.weixin.core.mp.client;

import java.net.URL;

import javax.xml.namespace.QName;

/**
 * 获取服务的实例工厂 获取的服务是单列的 java 客户端调用云端服务的方式，首先要通过wsdl2java
 * 命令将相关文件准备好，然后本地还需要相关cxf的jar包，
 * 
 * @author lvzh
 *
 */
public class MPServiceFactory {
	/**
	 * 客户端调用的实例对象 通过wsdl生成的接口对象
	 */
	private static final QName SERVICE_NAME = new QName("http://impl.mp.uap.yonyou.com/", "iMPMsgPubQryService");


	/**
	 * 提供给外部使用的获取服务获取实例的方法
	 * 
	 * @return
	 */
	public synchronized static IMPMsgPubQryService getServiceInstance() {
		URL wsdlURL = IMPMsgPubQryService_Service.WSDL_LOCATION;
		
		IMPMsgPubQryService_Service ss = new IMPMsgPubQryService_Service(wsdlURL, SERVICE_NAME);
		IMPMsgPubQryService port = ss.getMPMsgPubQryServiceImplPort();
	     return port;
	}
}
