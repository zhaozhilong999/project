package org.item.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.item.annotation.Login;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercept extends HandlerInterceptorAdapter{

	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        System.out.println("===========HandlerInterceptor1 preHandle"); 
        HandlerMethod hm =  (HandlerMethod) handler;//将其强转过来
        Login login = hm.getMethodAnnotation(Login.class);//拿到里面的自定义注解对象//通过反射
        if(login!=null){
        	System.out.println("有注解");
        }
        return true;  
    }  
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        System.out.println("===========HandlerInterceptor1 postHandle");  
    }  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        System.out.println("===========HandlerInterceptor1 afterCompletion");  
    }
}
