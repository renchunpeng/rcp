//: SimpleCollection.java
// A simple example using the new Collections
package com.inms.action.thinking_in_java;

import java.util.*;

public class CatsAndDogs {
	public static void main(String[] args) {
		Collection<String> cc = new ArrayList<String>();
		for (int i = 10; i < 20; i++)
			cc.add(Integer.toString(i));
		
		Collection<String> c = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
			c.add(Integer.toString(i));
		c.addAll(cc);
		boolean rcp = c.add(Integer.toString(20));
		boolean wq = c.remove(Integer.toString(21));
		System.out.println(rcp);
		System.out.println(wq);
//		if(!wq){
//			//System.out.println(c.size()+"包含数量");
//			Object[] irt = c.toArray(); 
//			for(int i = 0;i<irt.length;i++){
//				System.out.println(irt[i]);
//			}
//		}
		Iterator<String> it = c.iterator();
		System.out.println(it.getClass());
		while (it.hasNext())
			System.out.println(it.next());
	}
} // /:~