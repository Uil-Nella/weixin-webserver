package com.yonyou.weixin.core.verify;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.weixin.core.qq.AesException;
import com.yonyou.weixin.core.qq.WXBizMsgCrypt;

public class TokenVerify {
	public static String sToken = "xQtfQTeNBkcZI2uOjoftgyOROR1x";// 这个Token是随机生成，但是必须跟企业号上的相同
	public static String sCorpID = "wx569905b8c1a2a573";// 这里是你企业号的CorpID
	public static String sEncodingAESKey = "rjhGuYgsW7hc2AGI6wQoc8umXEL9zbrd7B6bVCXDes5";// 这个EncodingAESKey是随机生成，但是必须跟企业号上的相同

	public static void verify(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		// 微信加密签名
		String sVerifyMsgSig = request.getParameter("msg_signature");
		// 时间戳
		String sVerifyTimeStamp = request.getParameter("timestamp");
		// 随机数
		String sVerifyNonce = request.getParameter("nonce");
		// 随机字符串
		String sVerifyEchoStr = request.getParameter("echostr");
		String sEchoStr; // 需要返回的明文
		WXBizMsgCrypt wxcpt;
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		try {
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
					sVerifyNonce, sVerifyEchoStr);
			// 验证URL成功，将sEchoStr返回
			out.print(sEchoStr);
		} catch (AesException e1) {
			e1.printStackTrace();
		}
	}
}
