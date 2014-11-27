package com.yonyou.weixin.core.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.weixin.core.processor.YonyouProcessor;
import com.yonyou.weixin.core.qq.AesException;
import com.yonyou.weixin.core.qq.WXBizMsgCrypt;
import com.yonyou.weixin.core.util.MessageUtil;
import com.yonyou.weixin.core.verify.TokenVerify;


/**
 * 核心请求处理类
 */
public class YonyouServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TokenVerify.verify(request, response);
		
	}
	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        //-----------------------------------------------------------------
        // 调用核心业务类接收消息、处理消息  
        String respMessage = YonyouProcessor.process(request);  
        //-----------------------------------------------------------------  
        // 响应消息  
        PrintWriter out = response.getWriter();  
        try {
			respMessage=WXBizMsgCrypt.getWX().EncryptMsg(respMessage, request.getParameter("timestamp"), request.getParameter("nonce"));
		} catch (AesException e) {
			e.printStackTrace();
		}
        
        out.print(respMessage);  
        out.close();  
	}

}

