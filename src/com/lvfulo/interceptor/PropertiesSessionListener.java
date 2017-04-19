package com.lvfulo.interceptor;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lvfulo.utils.PropertiesUtils;


public class PropertiesSessionListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		PropertiesUtils.pps = new Properties();
		
		try {
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream is = new FileInputStream(path + "jdbc.properties");
			PropertiesUtils.pps.load(is);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
