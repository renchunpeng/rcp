package com.inms.action.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//线程类
public class xiancheng extends Thread {
	public void run() {
		try {
			String str = new String(); // 原有txt内容
			File f = new File("E:\\0.txt");
			int num = 0;
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				num++;
				if (num <= 5519) {
					continue;
				} else {
					sleep(600);
					if (!str.equals("")) {
						System.out.println(str); // 这里可以写你自己要运行的逻辑代码
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}