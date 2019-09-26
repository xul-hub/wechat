package com.mybatis.controler;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mybatis.utils.WeiBoServiceUtil;

import weibo4j.Friendships;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.UserCounts;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

@Controller
@RequestMapping("weibo")
public class WeiboLoginController {
	
	private static final String weibo_prefix = "/weibo/";
	
	@RequestMapping(value = { "/test" })
	public String weiboIndex(HttpServletRequest request) {
		return "redirect:https://api.weibo.com/oauth2/authorize?client_id=xxxxxxxx&response_type=code&redirect_uri=http://192.168.1.5/ssm/weibo/login";
	}
	  
	@RequestMapping(value = "/login",method = RequestMethod.GET)      
	public String weiboLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws WeiboException{
		 try {
			 String code = request.getParameter("code");
			 
			 if(code  == null || code.equals("") || code.equals("null")) {
				 return "redirect:https://api.weibo.com/oauth2/authorize?client_id=xxxxxxxx&response_type=code&redirect_uri=http://192.168.1.5/ssm/weibo/login";
			 }
			 
	         Oauth oauth = new Oauth(); 
	         String url =  oauth.authorize("code",null);
	         System.out.println("url:" + url + "<--->code:"+ code);
	         String token = WeiBoServiceUtil.getAccessTokenByCode(code).toString();
//	         String token = oauth.getAccessTokenByCode(code).toString();
	         System.out.println("token:"+token);
	         
             if(token  == null || token.equals("") || token.equals("null")) {
    			 return "redirect:https://api.weibo.com/oauth2/authorize?client_id=xxxxxxxx&response_type=code&redirect_uri=http://192.168.1.5/ssm/weibo/login";
    		 }
             
             String str[] = token.split(","); 
             String accessToken = str[0].split("=")[1];
             System.out.println("accessToken:"+accessToken);
             
             String str1[] = str[3].split("]");  
             String uid = str1[0].split("=")[1]; 
             System.out.println("uid:"+uid);
             
            
             /**
                                * 獲取用戶當前關注列表 粉絲列表
              */
            Friendships f = new Friendships(accessToken);
             UserWapper friendsByID = f.getFriendsByID(uid);
             List<User> u= friendsByID.getUsers();
             if(u != null && u.size() >0) {
            	 model.addAttribute("u", u);
             }
             
             /**
                                * 獲取用戶基本信息
              */
             Users users = new Users(accessToken);  
             User weiboUser = users.showUserById(uid);//获取用户名
             String name = weiboUser.getScreenName();//获取头像
             //String profileImageUrl =weiboUser.getProfileImageUrl();//获取个人描述
             String description =weiboUser.getDescription();
             String location =weiboUser.getLocation();//获取地址
             List<UserCounts> userCount = users.getUserCount(uid);
             if(userCount != null && userCount.size()>0) {
            	 model.addAttribute("userCount", userCount.get(0));
             }
             
             model.addAttribute("name", name);
             model.addAttribute("description", description);
             model.addAttribute("location", location);
             
         } catch (Exception e1) {
        	 e1.printStackTrace();
        	 return "redirect:https://api.weibo.com/oauth2/authorize?client_id=xxxxxxxx&response_type=code&redirect_uri=http://192.168.1.5/ssm/weibo/login";
         }
		 return weibo_prefix + "login";
	}
}



