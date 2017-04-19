package com.lvfulo.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  
  
  
public class CommonInterceptor extends HandlerInterceptorAdapter{  

	private static String[] excludePaths;// 不拦截的url
	//过滤
	private static String [] safeless = {"<script","</script","<iframe","</iframe","<frame","</frame","set-cookie","%3cscript",
		"%3c/script","%3ciframe","%3c/iframe","%3cframe", "%3c/frame","src=\"javascript:","<body","</body", "%3cbody","%3c/body",
		"%3c","%3e","%3c/","/%3e"};
	
    /* 
      
    private String mappingURL; 
     
    public void setMappingURL(String mappingURL) {     
               this.mappingURL = mappingURL;     
    }    
  */  
   
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
    	
        Enumeration params = request. getParameterNames ();

        boolean isSafe = true;
        String requestUrl = request. getRequestURI ();
        if (isSafe(requestUrl)) {
             requestUrl = requestUrl.substring(requestUrl.indexOf("/"));
             if (!excludeUrl(requestUrl)) {
                 while (params. hasMoreElements ()) {
                 String cache = request.getParameter((String) params.nextElement());
                     if (null != cache && cache.length() > 0) {
                         if (!isSafe(cache)) {
                             isSafe = false;
                             break;
                         }
                     }
                 }
             }

        }else{
             isSafe = false;
        }

        if (!isSafe) {
             request.setAttribute("error", "带有非法的参数");
             response. sendRedirect ("http://... ");
             return false;

        }
        return true;     
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

    private boolean excludeUrl (String url) {
        if (excludePaths != null && excludePaths.length > 0) {
             for (String path : excludePaths) {
                 if (url. toLowerCase (). equals(path)) {
                     return true;
                 }
             }
        }
        return false;
    }
    

//    @Override    
//    public void postHandle(HttpServletRequest request,    
//            HttpServletResponse response, Object handler,    
//            ModelAndView modelAndView) throws Exception { 
//    	System.out.println(new Date().getTime());
//        System.out.println("==============2postHandle================");    
//        if(modelAndView != null){  
//            modelAndView.addObject("var", postHandle");    
//        }    
//    }    
//    
   
//    @Override    
//    public void afterCompletion(HttpServletRequest request,    
//            HttpServletResponse response, Object handler, Exception ex)    
//            throws Exception {
//    	System.out.println(new Date().getTime());
//    	System.out.println("==============3afterCompletion================");    
//    }    
  
}  
