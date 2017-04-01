package com.inms.action.thinking_in_java;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.sun.xml.bind.v2.runtime.Name;

public class Adventure {
	public static void main(String[] args){
		rcp t1 = new rcp();
		
		System.out.println(t1.age);
		
		
		
	}
	


}

class rcp{
	private String Name = "任春鹏";
	public String age = "15";
}
