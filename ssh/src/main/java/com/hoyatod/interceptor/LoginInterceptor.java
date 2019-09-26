package com.hoyatod.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		// TODO Auto-generated method stub
		/*Object user = request.getSession().getAttribute(Global.USER_SESSION_KEY);
        if (user != null) {
            System.out.println("尚未登录，调到登录页面");
            response.sendRedirect("/ssh/index");
            return false;
        }*/
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle:" + handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion:" + handler );
	}
	
}
