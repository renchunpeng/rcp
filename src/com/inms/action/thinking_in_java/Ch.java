package com.inms.action.thinking_in_java;



public class Ch extends Fa{
//	 String cs = "child";
	public Ch(String ss){
		cs = ss;
	}
	public void p(){
		System.out.println(super.cs);
//		cs = "ww";
//		System.out.println(cs);
	}

	public static void main(String[] args){
		Ch ch = new Ch("qw");
		ch.p();
		System.out.println(ch.cs);
		
	} 
}  