package com.inms.action.complex;

public class ReturnTest{
	public static void main(String[] args){
		int x= name();
		System.out.println(x);
	}
	
	public static int name() {
		try {
			int x = 1/0;
		} catch (Exception e) {
			System.out.println("catch");
			return 1;
		}finally{
			System.out.println("finally");
		}
		return 0;
	}
}