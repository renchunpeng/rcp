package com.gpersist.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.gpersist.enums.DataBaseType;

public class ToolUtils {
	
	public static void main(String[] args){
		System.out.println("导出");
	}
	
	public static String GetBeginDate(String begin)
  {
		switch(GetDataBaseType()) {
			case Oracle10:
				return "to_date('" + begin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')";
			
			default:
				return "'" + begin + " 00:00:00'";
		}
  }
	
	public static DataBaseType GetDataBaseType() {
//	return DataBaseType.parse(PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_TYPE));
	return DataBaseType.parse("SqlServer2005");
}
	
  public static String GetEndDate(String end)
  {
  	switch(GetDataBaseType()) {
			case Oracle10:
				return "to_date('" + end + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
			
			default:
				return "'" + end + " 23:59:59'";
		}
  }
	
  public static String GetAndSearch(String search)
  {
    if (!StringIsEmpty(search))
      return " and ";
    else
      return "";
  }
	
	public static boolean StringIsEmpty(String val) {
		return (val == null || val.isEmpty());
	}
	
	public static Date GetMinDate() {
		return new java.util.Date(0);
	}

	public static <T> String CompareProperty(T source, T target, String[] propertynames) throws Exception {
		StringBuilder sb = new StringBuilder();

		for (String property : propertynames) {
			String[] propertys = property.split(":");

			if (propertys.length != 2)
				throw new Exception("对象比较时配置参数出错:" + property);

			Field fld = source.getClass().getDeclaredField(propertys[1]);
			fld.setAccessible(true);

			Object svalue = fld.get(source);
			Object tvalue = fld.get(target);

			if ((svalue != null) && (tvalue != null)) {
				if (!ConvertObjectValue(svalue).equals(ConvertObjectValue(tvalue)))
					sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "]-[" + ConvertObjectValue(tvalue) + "];" + WriteEnter());
			} else if ((svalue != null) && (tvalue == null)) {
				// source不为空 target为空
				sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "]-[空];" + WriteEnter());
			} else if ((svalue == null) && (tvalue != null)) {
				// source不为空 target为空
				sb.append(propertys[0] + "-" + propertys[1] + ":[空]-[" + ConvertObjectValue(tvalue) + "];" + WriteEnter());
			}
		}

		return sb.toString();
	}

	public static <T> String DebugProperty(T source, String[] propertynames) {
		StringBuilder sb = new StringBuilder();

		for (String property : propertynames) {
			String[] propertys = property.split(":");

			if (propertys.length == 2) {
				try {
					Field fld = source.getClass().getDeclaredField(propertys[1]);
					fld.setAccessible(true);

					Object svalue = fld.get(source);

					if ((svalue != null))
						sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "];" + WriteEnter());
					else {
						sb.append(propertys[0] + "-" + propertys[1] + ":[];" + WriteEnter());
					}

				} catch (Exception e) {

				}
			}
		}

		return sb.toString();

	}

	public static String ConvertObjectValue(Object in) {
		String rtn = "";

		if (in instanceof java.util.Date) {
			SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			rtn = sp.format(in);
		} else
			rtn = in.toString();

		return rtn;
	}

	public static String WriteEnter() {
		return WriteEnter(1);
	}

	public static String WriteEnter(int count) {
		String rtn = "";

		for (int i = 0; i < count; i++) {
			rtn += "\r\n";
		}
		return rtn;
	}
	
  public static String getRandomString(int length) { //length表示生成字符串的长度  
    String base = "abcdefghijklmnopqrstuvwxyz";     
    Random random = new Random();     
    StringBuffer sb = new StringBuffer();     
    for (int i = 0; i < length; i++) {     
        int number = random.nextInt(base.length());     
        sb.append(base.charAt(number));     
    }     
    return sb.toString();     
 }
}
