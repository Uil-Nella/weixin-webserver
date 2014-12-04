package com.yonyou.weixin.core.context;

import com.yonyou.weixin.core.user.model.Staff;
/**
 * 员工上下文环境   登录认证成功后即赋值
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月4日 上午9:39:03
 * <p> @version 0.0.1
 */
public class LoginContext {
	public static Staff  staff = null;
	
	public static void setLoginStaff(Staff  staff){
		LoginContext.staff = staff;
	}
}
