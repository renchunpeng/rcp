package com.lvfulo.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/*
 * 监听登录的整个过程 
 */
public class SessionListener implements HttpSessionBindingListener {

	public String privateInfo="";        //生成监听器的初始化参数字符串 
	private String logString="";        //日志记录字符串 
	private int count=0;        //登录人数计数器

	public SessionListener(String info){ 
	   this.privateInfo=info; 
	}

	public int getCount(){ 
	   return count; 
	}

	public void valueBound(HttpSessionBindingEvent event) 
	{ 
	   count++; 
	   if (privateInfo.equals("count")) { 
	       return; 
	   } 
       try { 
    	   Calendar calendar=new GregorianCalendar(); 
    	   System.out.println("LOGIN:"+privateInfo+" TIME:"+calendar.getTime()); 
    	   logString="\nLOGIN:"+privateInfo+" TIME:"+calendar.getTime()+"\n"; 
    	   for(int i=1;i<1000;i++) { 
    		   File file=new File("yeeyoo.log"+i); 
    		   if(!(file.exists())) {
    			   file.createNewFile();   //如果文件不存在，创建此文件 
    		   }
    		   if(file.length()>1048576) { //如果文件大于1M，重新创建一个文件 
    			   continue;
    		   }
    		   FileOutputStream foo=new FileOutputStream("yeeyoo.log"+i,true);//以append方式打开创建文件 
    		   foo.write(logString.getBytes(),0,logString.length()); //写入日志字符串 
    		   foo.close(); 
    		   break;//退出 
    	   } 
       } catch (FileNotFoundException e) {
    	   
       } catch (IOException e) {
    	   
       } 
	}

    public void valueUnbound(HttpSessionBindingEvent event) 
    { 
    	count--; 
    	if (privateInfo.equals("count")) { 
    		return; 
    	} 
    	try { 
    		Calendar calendar=new GregorianCalendar(); 
    		System.out.println("LOGOUT:"+privateInfo+" TIME:"+calendar.getTime()); 
    		logString="\nLOGOUT:"+privateInfo+" TIME:"+calendar.getTime()+"\n"; 
    		for(int i=1;i<1000;i++) { 
    			File file=new File("yeeyoo.log"+i); 
    			if(!(file.exists())) {
    				file.createNewFile();   //如果文件不存在，创建此文件 
    			}
    			if(file.length()>1048576) {//如果文件大于1M，重新创建一个文件 
    				continue; 
    			}
    			FileOutputStream foo=new FileOutputStream("yeeyoo.log"+i,true);//以append方式打开创建文件 
    			foo.write(logString.getBytes(),0,logString.length()); //写入日志字符串 
    			foo.close(); 
    			break;//退出 
    		} 
    	} catch (FileNotFoundException e) {
     	   
        } catch (IOException e) {
     	   
        } 
    }

}