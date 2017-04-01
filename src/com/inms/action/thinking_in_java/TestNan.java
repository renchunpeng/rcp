package com.inms.action.thinking_in_java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestNan{
	
	public static void main(String[] args){

		for(int i = 0;i<10;i++){
			test();
		}

	}

	private static void test() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );
		String datestr = sdf.format(new Date()); 
		String l = String.valueOf(System.nanoTime()).substring(3, 13);
//		String trString = "cu"+datestr+l;
//		String trString = l;
		System.out.println(UUID.randomUUID());
		
	}
	
	 
}