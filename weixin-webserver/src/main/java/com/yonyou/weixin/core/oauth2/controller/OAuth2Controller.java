package com.yonyou.weixin.core.oauth2.controller;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyou.weixin.core.context.LoginContext;
import com.yonyou.weixin.core.cxf.client.ServiceFactory;
import com.yonyou.weixin.core.cxf.client.YYUserWS;
import com.yonyou.weixin.core.model.AccessToken;
import com.yonyou.weixin.core.model.Result;
import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
import com.yonyou.weixin.core.oauth2.util.WeixinUtil;
import com.yonyou.weixin.core.user.model.Staff;
import com.yonyou.weixin.core.user.util.StaffUtil;

/**
 * OAuth2 处理控制器
 * <p/>
 * <p>
 * @author 刘新宇
 *
 * <p>
 * @date 2014年12月1日 下午6:30:24
 * <p>
 * @version 0.0.1
 */
@Controller
public class OAuth2Controller {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(OAuth2Controller.class);

	/**
	 * 构造参数并将请求重定向到微信API获取登录信息
	 * 
	 * @param index
	 * @return
	 */
	@RequestMapping(value = { "/oauth2.do", "/oauth2" })
	public String Oauth2API(HttpServletRequest request,
			@RequestParam String resultUrl) {
		if (logger.isInfoEnabled()) {
			logger.info("Oauth2API(HttpServletRequest, String)+自动认证拦截");
		}

		// 此处可以添加获取持久化的数据，如企业号id等相关信息
		String CropId = APPConstants.CORPID;
		String redirectUrl = "";
		if (resultUrl != null) {
			String reqUrl = request.getLocalAddr();
			reqUrl = reqUrl + "/" + APPConstants.APP_NAME;
			String backUrl = "http://" + reqUrl + "/oauth2url.do?oauth2url="
					+ resultUrl;
			System.out.println("backUrl=" + backUrl);
			redirectUrl = oAuth2Url(CropId, backUrl);
		}
		return "redirect:" + redirectUrl;
	}

	/**
	 * 根据code获取Userid后跳转到需要带用户信息的最终页面
	 * 
	 * @param request
	 * @param code
	 *            获取微信重定向到自己设置的URL中code参数
	 * @param oauth2url
	 *            跳转到最终页面的地址
	 * @return
	 */
	@RequestMapping(value = { "/oauth2url.do" })
	public String Oauth2MeUrl(HttpServletRequest request,
			@RequestParam String code, @RequestParam String oauth2url) {
		AccessToken accessToken = WeixinUtil.getAccessToken(
				APPConstants.CORPID, APPConstants.APPSECRET);
		HttpSession session = request.getSession();
		if (accessToken != null && accessToken.getToken() != null) {
			String Userid = getMemberGuidByCode(accessToken.getToken(), code,
					APPConstants.AGENTID);
			if (Userid != null) {
				Staff staff = StaffUtil.getStaffByUserId(Userid,
						accessToken.getToken());
				// 尝试拉取员工的与账号信息 （可能没有绑定）
				try {
					YYUserWS service = ServiceFactory.getServiceInstance();
					Object obj = service.findUser(staff.getUserid());
					if (obj != null) {
						JSONObject json = JSONObject.fromObject(obj);
						// 信息有效
						if (json.get("username") != null
								&& json.get("password") != null) {
							staff.setPdomaincode(json.get("username")
									.toString());
							staff.setPassword(json.get("password").toString());
						}
					}
				} catch (Exception e) {
					// 还没有绑定
				}
				// 这里简单处理,存储到session中
				session.setAttribute(APPConstants.CURRENT_LOGIN_STAFF, staff);
				LoginContext.setLoginStaff(staff);
			}
		}
		return "redirect:" + oauth2url;
	}

	/**
	 * 构造带员工身份信息的URL
	 * 
	 * @param corpid
	 *            企业id
	 * @param redirect_uri
	 *            授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * @param state
	 *            重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值
	 * @return
	 */
	private String oAuth2Url(String corpid, String redirect_uri) {
		try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri,
					APPConstants.APP_ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error("oAuth2Url(String, String)", e);
		}
		String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ corpid
				+ "&redirect_uri="
				+ redirect_uri
				+ "&response_type=code&scope=snsapi_base&state=xQtfQTeNBkcZI2uOjoftgyOROR1x#wechat_redirect";
		System.out.println("oauth2Url=" + oauth2Url);
		return oauth2Url;
	}

	/**
	 * 调用接口获取用户信息
	 * 
	 * @param token
	 * @param code
	 *            返回代码
	 * @param agentId
	 *            应用ID
	 * @return
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public String getMemberGuidByCode(String token, String code, int agentId) {
		System.out.println("code==" + code + "token=" + token + "agentid="
				+ agentId);
		Result<String> result = WeixinUtil.oAuth2GetUserByCode(token, code,
				agentId);
		System.out.println("result=" + result);
		if (result.getErrcode() == "0") {
			if (result.getObj() != null) {
				// 此处可以通过微信授权用code还钱的Userid查询自己本地服务器中的数据
				return result.getObj();
			}
		}
		return "";
	}

}
