package com.inms.action.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateRandom {

	public static void main(String[] args) {
//		String[] array = new String[100000000];
		List<String> list= new ArrayList<>();
		for (int i = 0; i < 100000000; i++) {
			list.add(test());
			if (!checkRepeat(list)) {
				System.out.println(i);
return;
			}
		}

	}

	static String test() {

		String[] array = new String[10];
		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 10);
			array[i] = String.valueOf(x);

		}
		String lString = "";
		for (int i = 0; i < array.length; i++) {
			lString += array[i];

		}
		return lString;
	}

	// 判断数组中是否有重复值
	static boolean checkRepeat(List<String> array) {
		Set<String> set = new HashSet<String>();
		for (String str : array) {
			set.add(str);
		}
		if (set.size() != array.size()) {
			System.out.println(set.size()+"   "+array.size());
			System.out.println("有重复");
			return false;// 有重复
		} else {
			System.out.println("不重复");
			System.out.println(set.size()+"   "+array.size());
			return true;// 不重复
		}
	}
}