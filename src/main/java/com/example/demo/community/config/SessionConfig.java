package com.example.demo.community.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionConfig implements HttpSessionListener {
	private static final Map<String,HttpSession> sessions = new ConcurrentHashMap<>();
	
	public synchronized static String getSessionIdCheck(String type , String compareId) {
		String result = "";
		for(String key : sessions.keySet()) {
			HttpSession hs = sessions.get(key);
			if(hs != null && hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareId)) {
				result =key.toString();
			}
		}
		
		return result;
	}
	
	public static void removeSessionForDoubleLogin(String userId) {
		if(userId != null && userId.length() >0) {
			sessions.get(userId).invalidate();
			sessions.remove(userId);
		}
	}
	
	
	@Override
	public  void sessionCreated(HttpSessionEvent se) {
	   sessions.put(se.getSession().getId(), se.getSession());
	}
	
	@Override
	public  void sessionDestroyed(HttpSessionEvent se) {
		if(sessions.get(se.getSession().getId()) != null) {
			sessions.get(se.getSession().getId()).invalidate();
			sessions.remove(se.getSession().getId());
		}
    }
	
}
