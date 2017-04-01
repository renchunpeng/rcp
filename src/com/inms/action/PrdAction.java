package com.inms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class PrdAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
    private String fileFileName;  
    private File file;  

    public String getFileFileName() {  
        return fileFileName;  
    }  
    public void setFileFileName(String fileFileName) {  
        this.fileFileName = fileFileName;  
    }  
    public File getFile() {  
        return file;  
    }  
    public void setFile(File file) {  
        this.file = file;  
    }  
 
public String filename = "";

    public InputStream  getRcp() throws FileNotFoundException{  
//       responseHtmlText("{success:'"+fileFileName+"'}"); 
    	File f = new File("D:\\"+"ss.jpg"); 
    	this.filename = "ss.jpg";
  		return new FileInputStream(f);
    }  
    
  	public String execute() throws Exception {
  		return "file";
  	}
      
    
    public void responseHtmlText(String text){  
      HttpServletResponse response = ServletActionContext.getResponse();  
      response.setContentType("text/html;charset=UTF8");  
      try {  
          response.getWriter().write(text);  
      } catch (IOException e) {  
          return;  
      }  
  }  


}





