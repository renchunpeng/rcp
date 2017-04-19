package com.lvfulo.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/** 
 * @ClassName: LoginListener 
 * @Description: 登录监听类-处理同一时间只允许账号，单地点登录 
 * @author lyp 
 */  
public class LoginListener implements HttpSessionAttributeListener {  
	
	//定义监听的session的属性名
	public final static String LISTENER_NAME = "user";
	
    //定义存放账号和session对应关系的map  
    private Map<String, HttpSession> map = new ConcurrentHashMap<String, HttpSession>();  
  
    //加入session的监听方法
    public void attributeAdded(HttpSessionBindingEvent event) {  
        String name = event.getName();  
  
        if (name.equals(LISTENER_NAME)) {  
//            CtmBase user = (CtmBase) event.getValue();  
//            if (map.get(user.getCustname()) != null) {  
//                HttpSession session = map.get(user.getCustname());  
//                session.removeAttribute(user.getCustname());  
//                session.invalidate();  
//            }  
//            map.put(user.getCustname(), event.getSession());  
        }  
  
    } 
    
    //session失效时的监听方法
    public void attributeRemoved(HttpSessionBindingEvent event) {  
        String name = event.getName();  
  
        if (name.equals(LISTENER_NAME)) {  
//        	CtmBase user = (CtmBase) event.getValue();  
//            map.remove(user.getCustname());  
  
        }  
    }  
  
    //session覆盖时的监听方法
    public void attributeReplaced(HttpSessionBindingEvent event) {  
  
    }  
  
    //返回登录session
    public Map<String, HttpSession> getMap() {  
        return map;  
    }  
  
    public void setMap(Map<String, HttpSession> map) {  
        this.map = map;  
    }  
  
}  
