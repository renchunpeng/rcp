package com.lvfulo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import sun.misc.BASE64Encoder;

import com.lvfulo.entity.gpersist.BaseBean;
import com.lvfulo.entity.gpersist.ReturnList;
import com.lvfulo.enums.DataBaseType;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class ToolUtils {

	public static DataBaseType GetDataBaseType() {
		return DataBaseType.parse(PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_TYPE));
	}

	public static Date GetMinDate() {
		return new java.util.Date(0);
	}

	public static Date GetNowDate() {
		return new java.util.Date();
	}

	public static boolean StringIsEmpty(String val) {
		return (val == null || val.isEmpty());
	}

	public static String GetNewLines() {
		return System.getProperty("line.separator");
	}

	public static String GetFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

		return sp.format(in);
	}

	public static String GetFmtDate(Date in, String fmt) {
		SimpleDateFormat sp = new SimpleDateFormat(fmt);

		return sp.format(in);
	}

	public static java.util.Date GetDateByFmt(String in, String fmt) {
		SimpleDateFormat sp = new SimpleDateFormat(fmt);

		try {
			return sp.parse(in);
		} catch (Exception e) {
			return new java.util.Date();
		}
	}

	public static String GetHMFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		return sp.format(in);
	}

	public static String GetDebugDate(Date in) {
		if (in == null)
			return "";

		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		return sp.format(in);
	}

	public static String GetLongFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		return sp.format(in);
	}

	public static java.util.Date String2Date(String in) throws ParseException {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		return sp.parse(in);
	}

	public static String GetJsonFromBean(Object bean, JsonConfig config) {
		JSONObject JsonObject = JSONObject.fromObject(bean, config);

		String rtn = JsonObject.toString();

		return rtn;
	}

	public static String GetJsonFromArray(Object bean, JsonConfig config) {
		JSONArray JsonObject = JSONArray.fromObject(bean, config);
		return JsonObject.toString();
	}

	public static String GetJsonFromArray(Object bean) {
		return JSONArray.fromObject(bean).toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> GetArrayFromJson(String json, Class<T> classtype) {
		JSONArray JsonObject = JSONArray.fromObject(json);

		String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd HH:mm:ss" };

		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

		Collection<T> collections = JSONArray.toCollection(JsonObject, classtype);
		List<T> lists = new ArrayList<T>();
		for (T t : collections) {
			if (classtype.equals(t.getClass()))
				lists.add(t);
		}
		return lists;
	}

	public static String GetMD5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString().toUpperCase();

		// try {
		// MessageDigest md = MessageDigest.getInstance("MD5");
		// md.update(str.getBytes());
		// byte b[] = md.digest();
		//
		// int i;
		//
		// StringBuffer buf = new StringBuffer("");
		// for (int offset = 0; offset < b.length; offset++) {
		// i = b[offset];
		// if (i < 0)
		// i += 256;
		// if (i < 16)
		// buf.append("0");
		// buf.append(Integer.toHexString(i));
		// }
		// // 32
		// return buf.toString();
		// // 16
		// // return buf.toString().substring(8, 24);
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/*
	 * public static void OutString(String str) { try { HttpServletResponse
	 * response = ((ServletRequestAttributes)
	 * RequestContextHolder.getRequestAttributes()).
	 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
	 * response.getWriter(); out.write(str); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public static void JsonOutString(String str) { try { HttpServletResponse
	 * response = ((ServletRequestAttributes)
	 * RequestContextHolder.getRequestAttributes()).getResponse();
	 * response.setContentType("text/json;charset=UTF-8"); PrintWriter out =
	 * response.getWriter(); out.write(str); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	public static String GetBeginDate(String begin) {
		switch (GetDataBaseType()) {
			case Oracle10:
				return "to_date('" + begin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')";

			default:
				return "'" + begin + " 00:00:00'";
		}
	}

	public static String GetEndDate(String end) {
		switch (GetDataBaseType()) {
			case Oracle10:
				return "to_date('" + end + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";

			default:
				return "'" + end + " 23:59:59'";
		}
	}

	public static String GetAndSearch(String search) {
		if (!StringIsEmpty(search))
			return " and ";
		else
			return "";
	}

	public static String Decode(String decode) {
		try {
			decode = URLDecoder.decode(decode, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return decode;
	}

	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String Encode(String encode) {
		try {
			encode = URLEncoder.encode(encode, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return encode;
	}

	public static String LeftFill(String Value, int Len) {
		return LeftFill(Value, Len, '0');
	}

	// / <summary>
	// / 按指定字符串左填充字符串
	// / </summary>
	// / <param name="Value">需要进行填充的字符串</param>
	// / <param name="Len">填充后的长度</param>
	// / <param name="Fill">指定填充的字符</param>
	// / <returns>填充后的字符串</returns>
	public static String LeftFill(String Value, int Len, char Fill) {
		int j = 0;
		if (StringIsEmpty(Value))
			j = 0;
		else
			j = Value.length();

		for (int i = 0; i < Len - j; i++) {
			Value = String.valueOf(Fill) + Value;
		}

		return Value;
	}

	// / <summary>
	// / 右填充字符串（默认填充0）
	// / </summary>
	// / <param name="Value">需要进行填充的字符串</param>
	// / <param name="Len">填充后的长度</param>
	// / <returns>填充后的字符串</returns>
	public static String RightFill(String Value, int Len) {
		return RightFill(Value, Len, '0');
	}

	// / <summary>
	// / 按指定字符串右填充字符串
	// / </summary>
	// / <param name="Value">需要进行填充的字符串</param>
	// / <param name="Len">填充后的长度</param>
	// / <param name="Fill">指定填充的字符</param>
	// / <returns>填充后的字符串</returns>
	public static String RightFill(String Value, int Len, char Fill) {
		int j = 0;
		if (StringIsEmpty(Value))
			j = 0;
		else
			j = Value.length();

		for (int i = 0; i < Len - j; i++) {
			Value += String.valueOf(Fill);
		}

		return Value;
	}

	public static int GetDaysBetween(java.util.Date beginDate, java.util.Date endDate) throws ParseException {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(beginDate);

		Calendar d2 = new GregorianCalendar();
		d2.setTime(endDate);

		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);

		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}

		return days;
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

	public static String GetErrorMessage(Exception e) {
		return GetErrorMessage(e, "");
	}

	public static String GetErrorMessage(Exception e, String prefix) {
		String rtn = "";

		if (e.getCause() instanceof SQLServerException) {

			SQLServerException sex = (SQLServerException) e.getCause();

			switch (sex.getErrorCode()) {
				case 18456:
					rtn = prefix + "数据库登录失败！";
					break;

				default:
					rtn = prefix + sex.getMessage();
					break;
			}
		} else if (e.getCause() instanceof java.sql.SQLException) {
			java.sql.SQLException oex = (java.sql.SQLException) e.getCause();

			switch (oex.getErrorCode()) {
				case 17002:
					rtn = prefix + "数据库登录失败！";
					break;

				default:
					rtn = prefix + oex.getMessage();
					break;
			}
		} else
			rtn = prefix + e.getMessage();

		return rtn.replace('"', '\'');
	}

	public static boolean MustComboValue(String value) {
		if (StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_MUST_VALUE))
			return true;

		return false;
	}

	public static boolean CheckComboValue(String value) {
		if (StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_MUST_VALUE))
			return true;

		if (value.equals(Consts.SELECT_ALL_VALUE))
			return true;

		return false;
	}

	public static boolean NullComboValue(String value) {
		if (StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_NULL_VALUE))
			return true;

		return false;
	}

	public static String GetIndentFmt(String value, String fmt, int indent) {
		String rtn = value;

		for (int i = 0; i < indent; i++) {
			rtn = fmt + rtn;
		}

		return rtn;
	}

	public static double GetDoubleByString(String in) {
		double rtn = 0;

		try {
			rtn = Double.valueOf(in);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rtn;
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

	public static String CryptSHA256(String in) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] hash = digest.digest(in.getBytes("UTF-8"));

			StringBuffer out = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					out.append('0');
				out.append(hex);
			}

			return out.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

	/**
	 * //数据库中有存\n\r的,数据读取时会读成\\n\\r,全部替换用 \\\\n\\\\r,原格式存取则会读取成\n\r,全部替换用\n\r
	 * 
	 * @param str
	 * @return
	 */
	public static String ReplaceSign(String str) {
		String result = "";
		if (!ToolUtils.StringIsEmpty(str))
			result = str.replaceAll("\n", "///n").replaceAll("\r", "///r").replaceAll("\t", "///t").replaceAll("\\\\n", "///n").replaceAll("\\\\r", "///r").replaceAll("\\\\t", "///t").replaceAll("\"", "“");

		return result;
	}

	/**
	 * 获取微信返回http处理方法
	 */
	public static String GetHttpRequest(String urlStr) {

		String result = "";
		try {

			URL url = new URL(urlStr);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 必须是get方式请求 24
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒28
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒29
																																				// 30
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer bs = new StringBuffer();
			String bufferline = null;
			while ((bufferline = bufferedReader.readLine()) != null) {
				bs.append(bufferline);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();

			System.out.println(bs.toString());
			result = bs.toString();

		} catch (ConnectException ce) {
			// log.error("连接超时：{}", ce);
		} catch (Exception e) {
			// log.error("https请求异常：{}", e);
		}
		return result;
	}

	// 调用ACW系统WebService接口通用方法
	public static String GetWebService(String serviceAddr, String nameSpace, String method) {
		String result = "";
		try {
			serviceAddr = "http://127.0.0.1:8080/nestle/ws/serviceuser?wsdl";
			nameSpace = "http://custom.webservice.nestle.com/";
			method = "SearchAddress";

			// 直接引用远程的wsdl文件
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(serviceAddr));
			call.setOperationName(new QName(nameSpace, method));

			call.addParameter("custid", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			call.setUseSOAPAction(true);

			// Object[] infos = new
			// Object[]{"[{\"custid\":\"01\",\"prdid\":\"001\",\"prdcount\":\"5\",\"createdate\":\"2015-10-10\",\"shopstatus\":\"01\",\"updatedate\":\"2015-10-10\"},{\"custid\":\"01\",\"prdid\":\"001\",\"prdcount\":\"5\",\"createdate\":\"2015-10-10\",\"shopstatus\":\"01\",\"updatedate\":\"2015-10-10\"},{\"custid\":\"01\",\"prdid\":\"001\",\"prdcount\":\"5\",\"createdate\":\"2015-10-10\",\"shopstatus\":\"01\",\"updatedate\":\"2015-10-10\"}]"};

			Object[] infos = new Object[] { "1475743319095" };

			result = (String) call.invoke(infos);

			URL url = new URL(serviceAddr); // 创建url地址
			URLConnection conn = url.openConnection(); // 打开连接
			HttpURLConnection httpConn = (HttpURLConnection) conn; // 转换成HttpURL

			httpConn.setDoInput(true); // 打开输入输出的开关
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST"); // 设置请求方式

			httpConn.setRequestProperty("Content-type", "text/xml;charset=UTF-8"); // 设置请求的头信息
			// 拼接请求消息
			String data = "<soapenv:Envelopexmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" + "xmlns:q0=\"http://server.rl.com/\"" + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body>" + "<q0:sayHello>" + "<arg0>renliang</arg0>" + "</q0:sayHello>" + "</soapenv:Body>" + "</soapenv:Envelope>";
			// 获得输出流
			OutputStream out = httpConn.getOutputStream();
			// 发送数据
			out.write(data.getBytes());
			// 判断请求成功
			if (httpConn.getResponseCode() == 200) {
				// 获得输入流
				InputStream in = httpConn.getInputStream();
				// 使用输入流的缓冲区
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer sb = new StringBuffer();
				String line = null;
				// 读取输入流
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				// 创建sax的读取器
				// SAXReader saxReader = new SAXReader();
				// //创建文档对象
				// Document doc = saxReader.read(new StringReader(sb.toString()));
				// //获得请求响应return元素
				// List<Element> eles = doc.selectNodes("//return");
				// for(Element ele : eles){
				// System.out.println(ele.getText());
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T> String OutLists(List<T> lists, int total, boolean hasdate) {
		ReturnList rtn = new ReturnList();

		JsonConfig config = new JsonConfig();
		if (hasdate)
			config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());

		if (lists.size() > 0) {
			T item = lists.get(0);
			config.registerPropertyExclusions(item.getClass(), ((BaseBean) item).OnExclusions());
		}

		rtn.setTotal(total);
		rtn.setData(ToolUtils.GetJsonFromArray(lists, config));

		return rtn.toString();
	}

	public static String ServiceMethod(String endpointurl, String serviceurl, String method, String[] addparam, Object[] param) {

		String result = "error";
		try {

			String endpoint = "http://127.0.0.1:8080/nestle/ws" + endpointurl;
			// 直接引用远程的wsdl文件
			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(new URL(endpoint));
			call.setOperationName(new QName(serviceurl, method));// WSDL里面描述的接口名称
			for (int i = 0; i < addparam.length; i++) {
				call.addParameter(addparam[i], org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			}
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			call.setUseSOAPAction(true);

			Object[] infos = param;
			result = (String) call.invoke(infos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getIpResult(String ip) {
		String result = "";
		try {
			String urlstr = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
			URL url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();// 打开连接端口
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 往对端写完数据对端服务器返回数据
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			result = buffer.toString();
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 生成随机数
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void OutObj(HttpServletResponse res, Object object) {
		PrintWriter out = null;
		try {
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			out = res.getWriter();
			out.print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}

	// MD5码加密
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}

	/**
	 * @description 将经纬度转化为城市名称保存到数据库
	 * @author jcxu
	 */

	public static String getAddress(String log, String lat) {
		String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
		String res = "";
		try {
			URL url = new URL(urlString);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.out.println("error in wapaction,and e is " + e.getMessage());
		}
		System.out.println(res);
		JSONObject result = JSONObject.fromObject(res);
		JSONObject positionObj = JSONObject.fromObject(JSONArray.fromObject(result.get("addrList")).discard(0));
		String position = positionObj.getString("admName") + "," + positionObj.getString("name");

		return position;
	}

	/**
	 * @author rcp
	 * @描述 将acw订单状态做对应
	 */

	public static String getOrderMessage(int x) {
		String[] message = new String[7];
		message[0] = "正在配送中";
		message[1] = "订单跟踪";
		message[2] = "商品正在配送中";
		message[3] = "订单已经取消";
		message[4] = "商品配送完成";
		message[5] = "没有成功";
		message[6] = "released";

		return message[x];

	}

	/**
	 * @author rcp
	 * @描述 根据传入的page确定start和end到底是多少
	 * 
	 */

	public static int getstart(String page) {
		int index = Integer.valueOf(page);
		return (index - 1) * 20;

	}

	/**
	 * 按比例扣除优惠券金额
	 * 
	 * @throws IllegalAccessException
	 */

	public static double kehcu(double chai, double sum, double youhui) throws IllegalAccessException {
		double x = Arith.savetwo(String.valueOf(chai / sum));
		double y = Arith.mul(String.valueOf(x), String.valueOf(youhui));
		double z = Arith.sub(String.valueOf(chai), String.valueOf(y));

		return z;

	}

	public static String formatdateString(int x) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(new Date().getTime() - 60 * 60 * 24 * x * 1000);
		String d = format.format(time);
		System.out.println("Format To String(Date):" + d);
		return d;
	}

	public static void main(String[] args) throws IllegalAccessException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(new Date().getTime());
		String d = format.format(time);
		System.out.println("Format To String(Date):" + d);
	}

	// public static String xml2JSON(String xml) {
	// JSONObject obj = new JSONObject();
	// try {
	// InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
	// SAXBuilder sb = new SAXBuilder();
	// Document doc = sb.build(is);
	// Element root = doc.getRootElement();
	// obj.put(root.getName(), iterateElement(root));
	// return obj.toString();
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	/**
	 * xml转化成jsonobject
	 */
	public static JSONObject xml2JSON(String orderXml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject json = (JSONObject) xmlSerializer.read(orderXml);
		return json;
	}

	public static String convertToString(Object obj) {

		JSONObject json = JSONObject.fromObject(obj);// 将java对象转换为json对象
		String str = json.toString();
		return str;
	}

}
