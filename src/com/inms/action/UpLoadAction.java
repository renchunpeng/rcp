package com.inms.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import sun.net.www.protocol.http.HttpURLConnection;

import com.opensymphony.xwork2.ActionSupport;

public class UpLoadAction extends ActionSupport {

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

	public void MyFile() {
		System.out.println("文件名：" + fileFileName);
		String dir = ServletActionContext.getServletContext().getRealPath(
				"/upload3");
		File dirtest = new File(dir);
		if (!dirtest.exists()) {
			dirtest.mkdir();
		}
		String ext = fileFileName.substring(fileFileName.lastIndexOf("."));
		long l = System.nanoTime();
		String realnameString = dir + "/" + l + ext;
		File newfile = new File(realnameString);

		file.renameTo(newfile);
		JSONObject rcpJsonObject = new JSONObject();
		rcpJsonObject.put("success", fileFileName);
		rcpJsonObject.put("time", new Date());
		responseHtmlText(rcpJsonObject.toString());
//		responseHtmlText("{success:'" + fileFileName + "'}");
	}

	public void responseHtmlText(String text) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			return;
		}
	}

	public void GetImage() {
		try {
			System.out.println(1);
			URL url = new URL("http://t12.baidu.com/it/u=1861318034,2616736646&fm=76");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据

			byte data[] = readInputStream(inStream);
			// inStream.read(data); //读数据
			// inStream.close();

			// File f = new File("D:\\"+"ss.jpg");
			// FileInputStream inStream= new FileInputStream(f);
			// byte data[] = readInputStream(inStream);

			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("image/jpg"); // 设置返回的文件类型
			OutputStream os = response.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
