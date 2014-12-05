package com.yonyou.weixin.core.verify;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
import com.yonyou.weixin.core.tencent.AesException;
import com.yonyou.weixin.core.tencent.WXBizMsgCrypt;
/**
 * token校验工具类
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年11月27日 下午8:00:15
 * <p> @version 0.0.1
 */
public class TokenVerify {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TokenVerify.class);

	/**
	 * 校验方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
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
			wxcpt = new WXBizMsgCrypt(APPConstants.TOKEN, APPConstants.encodingAESKey, APPConstants.CORPID);
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
					sVerifyNonce, sVerifyEchoStr);
			// 验证URL成功，将sEchoStr返回
			out.print(sEchoStr);
		} catch (AesException e1) {
			logger.error("verify(HttpServletRequest, HttpServletResponse)", e1);
		}
	}
}
