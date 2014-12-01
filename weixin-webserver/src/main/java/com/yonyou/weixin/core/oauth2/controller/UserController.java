package com.yonyou.weixin.core.oauth2.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.weixin.core.cxf.client.ServiceFactory;
import com.yonyou.weixin.core.cxf.client.YYUserWS;
import com.yonyou.weixin.core.message.model.SendMessage;
import com.yonyou.weixin.core.oauth2.annotation.OAuthRequired;
import com.yonyou.weixin.core.oauth2.inteceptor.APPConstants;
import com.yonyou.weixin.core.oauth2.util.RequestUtil;
import com.yonyou.weixin.core.oauth2.util.WeixinUtil;
/**
* 需要验证OAuth2控制器
* @author Sunlight
*
*/
@Controller
public class UserController {
	/**
	 * 请求中获取微信端id标识 key
	 */
	public static final String USERID = "Userid";
	/**
	 * 请求端json数据 标识key
	 */
	public static final String JSONDATA = "jsondata";
        /**
         * 加载个人信息，此处添加了@OAuthRequired注解
         * @param model
         * @return
         */
        @RequestMapping(value={"/userInfo.do"})
        @OAuthRequired
        public String load(HttpServletRequest request,Model model){
                HttpSession session = request.getSession();
                
				model.addAttribute(USERID, session.getAttribute(USERID));
                return "bind";
        }
        /**
         * 用于绑定员工信息
         * @param request
         * @param response
         * @throws IOException
         */
        @SuppressWarnings("static-access")
        @RequestMapping("/bindUser.do"  )  
        public void bindUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
        	 //解码  
			String str = URLDecoder.decode(request.getParameter(JSONDATA),APPConstants.APP_ENCODING);  
            JSONObject jb=new JSONObject();   
            YYUserWS service =  ServiceFactory.getServiceInstance();
			String userid=(String)jb.fromObject(str).get("userid");  
            String username=(String)jb.fromObject(str).get("username");
            String password=(String)jb.fromObject(str).get("password");
            JSONObject jsonObject = new JSONObject();  
            if(!service.findUser(userid).contains("null")){
            	jsonObject.put("msg", "该员工已经绑定过，不能再次绑定");
            	jsonObject.put("status", "failure");
            }else{
            	service.bindUser(userid, username, password);
            	jsonObject.put("msg", "绑定成功"); 
            	jsonObject.put("status", "success");
            	//返回给微信客户端提示
            }
            response.getWriter().print(jsonObject.toString()) ;
            
       	 // 调取凭证
      	   String access_token = WeixinUtil.getAccessToken(APPConstants.CORPID, APPConstants.APPSECRET).getToken();
      	   // 回复文本消息
      	   String PostData = SendMessage.STextMsg(userid, "", "", "1", "您已绑定，如需修改绑定信息请回复： ${域账号@密码}");
      	   int result = RequestUtil.PostMessage(access_token, "POST", SendMessage.POST_URL, PostData);
      	   // 打印结果
      		if(0==result){
      			System.out.println("操作成功");
      		}
      		else {
      			System.out.println("操作失败");
      		}
        }
}