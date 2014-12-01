package com.yonyou.weixin.core.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.weixin.core.dispatcher.YonyouDispatcher;
import com.yonyou.weixin.core.tencent.AesException;
import com.yonyou.weixin.core.tencent.WXBizMsgCrypt;
import com.yonyou.weixin.core.verify.TokenVerify;
/**
 * 微信请求控制器
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年11月27日 下午7:36:44
 * <p> @version 0.0.1
 */
@Controller
public class YonyouCoreController {
	/**
	 * get请求 主要用于校验
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/core.do" }, method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//第一次需要校验
		TokenVerify.verify(request, response);
	}
	
	/**
	 * post请求用于分发处理各种事件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/core.do" }, method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//------------------------------------------------------------------
		// 调用核心业务类接收消息、处理消息
		String respMessage = YonyouDispatcher.process(request);
		// -----------------------------------------------------------------
		// 响应消息
		PrintWriter out = response.getWriter();
		try {
			//使用AES加密响应信息
			respMessage = WXBizMsgCrypt.getDefaultInstance().EncryptMsg(respMessage,
					request.getParameter("timestamp"),
					request.getParameter("nonce"));
		} catch (AesException e) {
			e.printStackTrace();
		}

		out.print(respMessage);
		out.close();
	}
}
