package com.yonyou.weixin.core.oauth2.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.weixin.core.cxf.client.BindUser;
import com.yonyou.weixin.core.cxf.client.ServiceFactory;
import com.yonyou.weixin.core.cxf.client.YYUserWS;
import com.yonyou.weixin.core.oauth2.annotation.OAuthRequired;
/**
* 需要验证OAuth2控制器
* @author Sunlight
*
*/
@Controller
public class UserController {
        /**
         * 加载个人信息，此处添加了@OAuthRequired注解
         * @param model
         * @return
         */
        @RequestMapping(value={"/userInfo.do"})
        @OAuthRequired
        public String load(HttpServletRequest request,Model model){
                System.out.println("Load a User!");
                HttpSession session = request.getSession();
                model.addAttribute("Userid", session.getAttribute("Userid"));
                return "bind";
        }
        @RequestMapping("/bindUser.do"  )  
        public void bindUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
        	 //解码  
            String str = URLDecoder.decode(request.getParameter("jsondata"),"UTF-8");  
            JSONObject jb=new JSONObject();   
            YYUserWS service =  ServiceFactory.getServiceInstance();
            String userid=(String)jb.fromObject(str).get("userid");  
            String username=(String)jb.fromObject(str).get("username");
            String password=(String)jb.fromObject(str).get("password");
            JSONObject jsonObject = new JSONObject();  
            if(!service.findUser(userid).contains("null")){
            	jsonObject.put("msg", "该员工已经绑定过，不能再次绑定");
            }else{
            	service.bindUser(userid, username, password);
            	jsonObject.put("msg", "绑定成功"); 
            }
            response.getWriter().print(jsonObject.toString()) ;   
        }
        
        
      //通过该函数返回json格式的数据，在前台通过JQuery进行解析  
        @RequestMapping("/resolveJson"  )  
        public void resolveJson(HttpServletRequest request,HttpServletResponse response) throws IOException {  
              
            List m = (List) new  ArrayList();  
            JSONArray jsons = new JSONArray();  
            for(int i=0;i<10;i++){  
//                User user = new User();  
//                user.setUserName("name_" + i);  
//                m.add(user);  
            }  
              
            for(int j=0;j<m.size();j++){  
                JSONObject jsonObject = new JSONObject();  
                jsonObject.put("user", m.get(j));  
                jsons.add(jsonObject);  
            }  
            response.getWriter().print(jsons.toString()) ;   
        }   
          
        @RequestMapping("/toJson"   )   
        public String toJson() {  
            return "/json";  
        }  
}