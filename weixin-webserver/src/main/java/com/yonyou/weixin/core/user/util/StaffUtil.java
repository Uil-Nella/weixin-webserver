package com.yonyou.weixin.core.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.yonyou.weixin.core.context.LoginContext;
import com.yonyou.weixin.core.oauth2.enums.EnumMethod;
import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
import com.yonyou.weixin.core.oauth2.util.HttpRequestUtil;
import com.yonyou.weixin.core.oauth2.util.WeixinUtil;
import com.yonyou.weixin.core.user.model.Staff;
/**
 * 企业员工工具类
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月3日 下午1:19:29
 * <p> @version 0.0.1
 */
public class StaffUtil {
	public static String STAFF_API_URL="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USER_ID";
	/**
	 * 根据id获取员工信息
	 * @param userId 员工id
	 * @param access_token 
	 * @return
	 */
	public static Staff getStaffByUserId(String userId,String access_token){
		String url = STAFF_API_URL.replace("ACCESS_TOKEN", access_token).replace("USER_ID", userId);
		JSONObject jo = HttpRequestUtil.httpRequest(url, EnumMethod.GET.name(), null);
		Staff staff = new Staff();
		if(jo.get("errcode")!=null&&!jo.get("errcode").equals(0)){
			//获取员工信息出错
			System.out.println("jo="+jo);
		}else{
			jo.remove("errcode");
			jo.remove("errmsg");
			staff.setExtattr(jo.getString("extattr").toString());
			jo.remove("extattr");
			staff = (Staff)JSONObject.toBean(jo, Staff.class);
		}
		return staff;
	}
	/**
	 * 重新加载信息
	 * @param request
	 * @param username
	 * @param password
	 */
	public static void reLoad(HttpServletRequest request,String username,String password){
		HttpSession session = request.getSession();
		Staff staff = (Staff)session.getAttribute(APPConstants.CURRENT_LOGIN_STAFF);
		staff.setPdomaincode(username);
		staff.setPassword(password);
		LoginContext.setLoginStaff(staff);
	}
	
	public static void main(String[] args) {
		getStaffByUserId("0000049835", WeixinUtil.getAccessToken(APPConstants.CORPID, APPConstants.APPSECRET).getToken());
	}
}
