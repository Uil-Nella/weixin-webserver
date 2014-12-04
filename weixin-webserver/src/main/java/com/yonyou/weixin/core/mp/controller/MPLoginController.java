package com.yonyou.weixin.core.mp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.weixin.core.mp.util.HexUtil;
import com.yonyou.weixin.core.mp.util.MPUtils;
import com.yonyou.weixin.core.oauth2.annotation.OAuthRequired;
import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
import com.yonyou.weixin.core.user.model.Staff;

@Controller
public class MPLoginController {
	/**
	 * 请求中获取微信端id标识 key
	 */
	public static final String USERID = "UserId";
	public static final String MP_SERVER_IP = "http://223.203.195.180:88/index";
	public static final String MP_WX_SING="wx";
        /**
         * mp自动登录
         * @param model
         * @return
         * @throws Exception 
         */
        @RequestMapping(value={"/mplogin.do","/mplogin"})
        @OAuthRequired
        public String login(HttpServletRequest request,Model model) throws Exception{
                HttpSession session = request.getSession();
                Staff staff = (Staff) session.getAttribute(APPConstants.CURRENT_LOGIN_STAFF);
                //Session中没有用户信息 则重新加载
                if(null==staff){
                	return "redirect:"+MP_SERVER_IP;
                }
                if(StringUtils.isEmpty(staff.getUserid())){
                	//加载用户信息
                }
                //根据userId获取域账号信息
                if(StringUtils.isNotEmpty(staff.getPdomaincode())){
                	String url = MP_SERVER_IP+"?"+MP_WX_SING+"="+HexUtil.bytesToHex(MPUtils.encryptDES((staff.getPdomaincode()+"|"+String.valueOf(System.currentTimeMillis()))));
                	String redirectUrl = "redirect:"+url;
                	//自动登录
                	return redirectUrl;
                }else{
                	return "redirect:"+MP_SERVER_IP;
                }
        }
     
}