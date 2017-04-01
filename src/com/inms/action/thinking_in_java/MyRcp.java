package com.inms.action.thinking_in_java;


	interface aa{
		String rcpString = "rcp";
		void printaa();
	}
 
	
	class r1 implements aa{
		@Override
		public void printaa() {
			System.out.println("r1");
		}
		
	}
	
	class r2 implements aa{
		@Override
		public void printaa() {
			System.out.println("r2");			
		}	
	}
	
	 class r3 implements aa{
		@Override
		public void printaa() {
			System.out.println("r3");			
		}		
	}


	public class MyRcp{
		 static void methodrcp (aa aat){
			aat.printaa();
			System.out.println(aa.rcpString);
		}
		
	public static void main(String[] args){

		aa test = new r1();
		aa test2 = new r2();
		r3 test3 = new r3();
		
		methodrcp(test);
		methodrcp(test2);
		methodrcp(test3);
	}
	}
