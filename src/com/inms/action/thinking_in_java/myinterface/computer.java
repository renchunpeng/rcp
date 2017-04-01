package com.inms.action.thinking_in_java.myinterface;

class zhujiimp implements zhuji{

	@Override
	public void jisuan() {
		System.out.println("中等计算能力！");
		
	}
	
}

class xianshiimp implements xianshi{

	@Override
	public void play() {
		System.out.println("中等显示能力！");
		
	}
	
}

class xianshiimp2 implements xianshi{

	@Override
	public void play() {
		System.out.println("下等显示能力！");
		
	}
	
}

public class computer{
	
	zhuji tt1;
	xianshi tt2;

	public computer(zhuji t1,xianshi t2) {
		tt1 = t1;
		tt2 = t2;
	}
	
	public void run(){
		tt1.jisuan();
		tt2.play();
	}
	
	public static void main(String[] args){
		zhuji zhong = new zhujiimp();
		xianshi zhong2 = new xianshiimp2();
		computer rcp = new computer(zhong, zhong2);
		rcp.run();
	}
}