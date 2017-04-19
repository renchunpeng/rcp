package com.lvfulo.controller.returnmoney.alipay.config;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String app_id = "2016101102094331";

  public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMZ7GCknnufavFsF1anvZ5OV8oz3yW0jDlmaxGqSOpbe0Z51PhSGapj9xDYfxyWwbyALFTZp7qe3wO6NJb8vP6G6DNzpAFXt3uf3chGSt+NPk8w4O0IJ8yPxgX4XV6C+ZHgPe40E34dQe6we/VLGvA/cph/pFE/ALhlUXpSLmVGtAgMBAAECgYA2nQ5T3KwT3hMv9JYLMW3NpVfdRB0vo5LCQxzCukPW2a7SLC6N1UKloEYKIopYq4iYR5RpqZJaoew7d6koRyb5q6/evv+s8y3jH4ee35b7HwGp3CaT13oNPWVYkkaR9sdJfJh55gpiJNNyfUe7bZhhUkiLouQyEldQKIO1sCvjIQJBAPeeJzWdgrHB0duQBPsMsv0dk6KoBHTWAGyEPsPKVuhujpzPvVQmf8SHdSh4Wi0B8kx7KL70gBqigYpsfxXAk8kCQQDNMx9Hmj9wtnx1I1wzpPXykc5e+Gq9Y9NchZLrQB1PwqjtiixpnCp+qG5RW5RB068behxEZ1/qPDcEDXODBtjFAkBpgUWLnrVVMBROzSqSHXMWTkfdgmGDtrsvgZmMquYk2xaA0jMFa/H8twrAV6d0jePDmqZd1qfOsXcol9n+VrOhAkBAb+FOtt1dI7EJh8UYYqDpP2gy4oeCWaqxzzBHt6Z8uYAat90ifOvLQlECM2PnF/ZRBpOB14SDJc2Q9Dhl2OyRAkEA9tnZWT20gBVY5j8tfWUMIMNtMnGs3Jmjk25QW3FCJbEg19Q22utvHpnYixyy/WQqJQt9rqzGLyRwDBNyt7hqDQ==";
    
  public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	public static String content_type = "json";
	
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "GBK";
		
	// 调用的接口名，无需修改
	public static String alipayurl = "https://openapi.alipay.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

