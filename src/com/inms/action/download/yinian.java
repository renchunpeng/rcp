package com.inms.action.download;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class yinian {
	public static String urlString = "http://www.biqukan.com";
	public static String urlString2 = "/1_1094/7558903.html";

	public static void body() {

		String httpurl = "";
		try {
			for (int i = 0; i < 300; i++) {
				System.out.println("页码:" + i);
				httpurl = urlString + urlString2;
				Document doc = Jsoup.connect(httpurl).timeout(10000) // 设置连接超时时间
						.get(); // 使用 POST 方法访问 URL

				String title = doc.getElementsByClass("content").get(0).getElementsByTag("h1").get(0).text();// 标题---用text方法获取文本值
				Element els = doc.getElementById("content");// 主要内容
				Element page_chapter = doc.getElementsByClass("page_chapter").get(0);// 下一页

				int x = i / 50;
				contentToTxt("E:\\" + x + ".txt", title + "\r\n");
				contentToTxt("E:\\" + x + ".txt", els.html().toString().replace("<br />", "\r\n").replace("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", ""));
				contentToTxt("E:\\" + x + ".txt", "\r\n");

				urlString2 = page_chapter.getElementsByTag("a").get(2).attr("href");// 用attr获取属性值
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
				// System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\r\n";
			}
			input.close();
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		body();
	}
}