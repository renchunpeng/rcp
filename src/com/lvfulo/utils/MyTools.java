package com.lvfulo.utils;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import com.gpersist.entity.BaseBean;

public class MyTools {

	/**
	 * @author rcp
	 * @描述 根据传进来的数字传出几位随机数
	 */
	public static String Randomtest(int in) {
		String[] array = new String[in];
		for (int i = 0; i < in; i++) {
			int x = (int) (Math.random() * 10);
			array[i] = String.valueOf(x);

		}
		String l = "";
		for (int i = 0; i < array.length; i++) {
			l += array[i];

		}
		return l;
	}

	/**
	 * @author rcp
	 * @描述 根据传进来的（例如TR）生成编号
	 */
	public static String CreateID(String flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datestr = sdf.format(new Date());

		String[] array = new String[10];
		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 10);
			array[i] = String.valueOf(x);

		}
		String l = "";
		for (int i = 0; i < array.length; i++) {
			l += array[i];

		}
		String trString = flag + datestr + l;
		return trString;
	}

	/**
	 * @author rcp
	 * @描述把json数组转化为javabean
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> GetArrayFromJson(String json, Class<T> classtype) {
		JSONArray JsonObject = JSONArray.fromObject(json);

		String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd" };

		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

		Collection<T> collections = JSONArray.toCollection(JsonObject, classtype);
		List<T> lists = new ArrayList<T>();
		for (T t : collections) {
			if (classtype.equals(t.getClass()))
				lists.add(t);
		}
		return lists;
	}

	/**
	 * @author rcp
	 * @描述把json对象转化为javabean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T GetOneFromJson(String json, Class<T> classtype) {
		JSONObject JsonObject = JSONObject.fromObject(json);

		String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd" };

		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

		T item = (T) JSONObject.toBean(JsonObject, classtype);
		return item;
	}

	/**
	 * @author rcp
	 * @描述把java数组转化为json
	 */
	public static <T> String OutLists(List<T> lists, boolean hasdate) {

		JsonConfig config = new JsonConfig();
		if (hasdate)
			config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());

		if (lists.size() > 0) {
			T item = lists.get(0);
			config.registerPropertyExclusions(item.getClass(), ((BaseBean) item).OnExclusions());
		}

		return ToolUtils.GetJsonFromArray(lists, config);

	}

	/**
	 * @author rcp
	 * @描述把java对象转化为json
	 */
	public static <T> String OutListOne(T item, boolean hasdate) {

		JsonConfig config = new JsonConfig();
		if (hasdate)
			config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
		JSONObject JsonObject = JSONObject.fromObject(item, config);

		return JsonObject.toString();

	}

	/**
	 * @author rcp
	 * @描述 将传入的两个经纬度之间的距离算出来
	 */
	// private const double EARTH_RADIUS = 6378.137;
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)// 维度，精度，维度，精度
	{
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137;// 单位是KM，所以算出来的也是KM
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 判断请求来自linux还是windows
	 * 
	 * @return
	 */
	public static boolean IsLinux() {
		boolean flag = false;

		try {
			URL ut = Thread.currentThread().getContextClassLoader().getResource("");
			String path = ut.toString().replace("file:/", "");
			if (path.indexOf(Consts.linuxflag) >= 0) {
				flag = true;
			} else {

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	public static void main(String args[]) {
		System.out.println(GetDistance(31.998410, 118.750153, 25.047595, 102.710887));
	}

}