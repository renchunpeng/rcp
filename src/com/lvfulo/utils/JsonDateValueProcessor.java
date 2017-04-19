package com.lvfulo.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import java.util.Date;

public class JsonDateValueProcessor implements JsonValueProcessor {

	public JsonDateValueProcessor() {

	}

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		// TODO Auto-generated method stub
		String[] obj = {};
		if (value instanceof Date[]) {
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = "new Date(" + dates[i].getTime() + ")";
			}
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		// TODO Auto-generated method stub
		if (value instanceof Date) {
			return "new Date(" + ((Date) value).getTime() + ")";
		}

		return value == null ? null : value.toString();
	}

}
