package com.inms.action.thinking_in_java;


public class supertest {

	public static void main(String[] args) {

		child c = new child();
		c.father = "rcp";
		c.c();


	}
}

class father {
	String father = "123";

	public void f() {
		System.out.println(father);
	}
}

class child extends father {

	public void f(){
		System.out.println("wq");
	}
	
	public void c() {
		this.f();
		System.out.println(super.father);
	}
}
