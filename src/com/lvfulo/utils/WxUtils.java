package com.lvfulo.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WxUtils {
	
	 /**
	  * 将上传参数拼接成xml格式字符串
	  * @param parameters
	  * @return
	  */
	 public static String getRequestXml(SortedMap<Object, Object> parameters) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
	 }
	 
	 /**
	  * 获取微信返回的xml
	  * @return
	  */
	 public static String getReponseXml(String url, String paramXml) {
	        BufferedReader reader = null;
	        String result = "";
	        try {  
	        	HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();  
	             //加入数据    
	            conn.setRequestMethod("POST");    
	            conn.setDoOutput(true);    
	                
	            BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());    
	            buffOutStr.write(paramXml.getBytes());  
	            buffOutStr.flush();    
	            buffOutStr.close();    
	                
	            //获取输入流    
	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));    
	                
	            String line = null;    
	            StringBuffer sb = new StringBuffer();    
	            while((line = reader.readLine())!= null){    
	                sb.append(line);    
	            }    
	            
	            result = sb.toString();
	            System.out.println("===================="+sb.toString());
	                        
	           
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return result;		 
	 }
	 
	 
	 public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
			// 将解析结果存储在HashMap中
			Map<String, String> map = new HashMap<String, String>();

			// 从request中取得输入流
			// InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());

			// 释放资源
			inputStream.close();
			inputStream = null;

			return map;
		}
	
}