package com.lvfulo.utils;

import net.sf.json.processors.PropertyNameProcessor;

public class FirstPropertyNameProcessor implements PropertyNameProcessor {

	@SuppressWarnings("rawtypes")
	@Override	
	public String processPropertyName(Class baseClass, String name) {
		char[] chars = name.toCharArray();
		if (chars.length > 0)
			chars[0] = Character.toLowerCase(chars[0]);

		return new String(chars);
	}
}
