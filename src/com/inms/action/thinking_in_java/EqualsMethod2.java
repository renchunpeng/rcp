package com.inms.action.thinking_in_java;



public class EqualsMethod2 {
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
		if(i == 74) break; // Out of for loop
		if(i % 9 != 0) continue; // Next iteration
		System.out.println(i);
		}
		int i = 0;
		// An "infinite loop":
		while(true) {
		i++;
		int j = i * 27;
		if(j == 1269) break; // Out of loop
		if(i % 10 != 0) continue; // Top of loop
		System.out.println(i);
		}
		}
}
