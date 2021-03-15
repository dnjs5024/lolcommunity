package com.example.demo.community.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAOP {
	String[] excludes = {"/views/user/login","/views/community/freeBoard","/views/community/freeView","/views/community/guideBoard","/views/community/guideView","/views/user/login"};
	
	public boolean hasExclude(String uri) {
		for(String exclude : excludes) {
			if(uri.equals(exclude)) {
				return false;
			}
		}
		return true;
	}
	
	
	@Around("execution(* com.example.demo.community.controller.common.URIController.goPage(..))")
	public Object loginCheck(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for(Object arg : args) {
			if(arg instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) arg;
				String uri = req.getRequestURI();
				boolean isCheck = hasExclude(uri);
				
				if(isCheck) {
					HttpSession hs =req.getSession();
					if(hs.getAttribute("user")==null) {
						return "redirect:/views/user/login";
					}
				}
			}
		}
		Object obj = pjp.proceed(); 
		return obj;
	}

}
