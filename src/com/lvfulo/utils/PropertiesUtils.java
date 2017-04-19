package com.lvfulo.utils;

import java.util.Properties;

public class PropertiesUtils {
	public static Properties pps;
	
	public static String GetString(String key) {
		String value = pps.getProperty(key);

		if (value == null) {
			// throw new Exception();
		}
		return value;
	}
	
	public static int GetInt(String key) {
		try {
			return Integer.parseInt(GetString(key));
		} catch (Exception ex) {
			// throw new PropertiesConfigException(nf.getMessage(),key);
		}
		
		return 0;
	}
}

