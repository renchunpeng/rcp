package com.lvfulo.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author lyp
 *登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	//过滤
	private static String [] safeless = {"<script","</script","<iframe","</iframe","<frame","</frame","set-cookie","%3cscript",
		"%3c/script","%3ciframe","%3c/iframe","%3cframe", "%3c/frame","src=\"javascript:","<body","</body", "%3cbody","%3c/body",
		"%3c","%3e","%3c/","/%3e"};
	//日志
	protected Logger log = Logger.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handle) throws Exception {
		
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8"); 
        
        
		//创建session
		HttpSession session =request.getSession();
		
		//无需登录，允许访问的地址
		String[] allowUrls =new String[]{"lflweb/index.html","lflweb/css","lflweb/font","lflweb/img"};
			
		//获取请求地址
		String url =request.getRequestURL().toString();
		
        Enumeration params = request. getParameterNames ();

        boolean isSafe = true;
        if (isSafe(url)) {
        	url = url.substring(url.indexOf("/"));
             while (params. hasMoreElements ()) {
             String cache = request.getParameter((String) params.nextElement());
                 if (null != cache && cache.length() > 0) {
                     if (!isSafe(cache)) {
                         isSafe = false;
                         break;
                     }
                 }
             }
        }else{
             isSafe = false;
        }

        if (!isSafe) {
             request.setAttribute("error", "带有非法的参数");
             response.sendRedirect(request.getContextPath()+"/prd/index");
             return false;

        }
        
        //TODO  正式上线修改
		//boolean beFilter = false; 
        boolean beFilter = true;
		
		//获得session中的用户
//		CtmBase ctmBase =(CtmBase) session.getAttribute("user");
//		if(ctmBase ==null)				
//		{
//			for (String strUrl : allowUrls) {
//				if(url.contains(strUrl))
//				{
//					beFilter =  true;
//					break;
//				}
//			}			
//		}
//		else {
//			if(url.contains("/prd/index")) {
//				response.sendRedirect(request.getContextPath()+"/user/login");
//			}
//			beFilter =  true;			
//		}
		//TODO 正式上线时放开
//		if (!beFilter) {
//			response.sendRedirect(request.getContextPath()+"/prd/index");
//		}
        return beFilter;		
	}

    private static boolean isSafe (String str) {
        if (null != str && str. length () > 0) {
             for (String s: safeless) {
                 if (str. toLowerCase ().contains(s)) {
                     return false;
                 }
             }
        }
        return true;
    }
    
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}