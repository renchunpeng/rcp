package com.inms.action.suanfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hanoi {
	public static void main(String[] args) throws IOException {
		File file = new File("F:/笔记和项目文档/项目文档/nestle");
		listF(file);

	}

	public static void listF(File file) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					listF(files[i]);
				}
			}
		}
		if (file != null) {
			// System.out.println(file.getAbsolutePath());
			String fileName = file.getName();
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if (prefix.equals("docx")) {
				System.out.println(file.getAbsolutePath());
				aa(file.getAbsolutePath());
			}
		}
	}

	public static void aa(String name) throws IOException {

		/**
		 * 读TXT文件内容
		 * 
		 * @param fileName
		 * @return
		 */

		String result = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			// fileReader = new FileReader(name);
			// bufferedReader = new BufferedReader(fileReader);
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(name), "utf-8"));
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					result = result + read + "\r\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		System.out.println("读取出来的文件内容是：" + "\r\n" + result);

	}
}
