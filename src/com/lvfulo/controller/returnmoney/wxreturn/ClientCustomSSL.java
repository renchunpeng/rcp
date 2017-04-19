package com.lvfulo.controller.returnmoney.wxreturn;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.gpersist.utils.ToolUtils;
import com.lvfulo.utils.Consts;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {

	// 商户号
	// 微信公众平台：“微信支付”--》“商户信息”--》“商户号”，将该值赋值给partner
	private static String partner = Consts.JH_MCHID;
	// p12证书的位置
	// 微信公众平台：“微信支付”--》“商户信息”--》“交易数据”--》“详情请登录微信支付商户平台查看”（登录）--》“API安全”--》“API证书”--》“下载证书”
	// 下载证书后将apiclient_cert.p12放在src目录下面（出于安全考虑，请自行下载自己的证书）
	private static String apiclient_certLocation = "apiclient_cert.p12";

	public static String doRefund(String url, String app_id, String data) throws Exception {
		
		//region 如果有多个账户需要区分，这里不需要
//		if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx77839b105d6b3221")) {
//			partner = "1401092202";
//			apiclient_certLocation = "apiclient_cert.p12";
//		} else if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx793589bff5d401cd")) {
//			partner = "1399211002";
//			apiclient_certLocation = "apiclient_cert1.p12";
//		} else if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx173183ea84a24afd")) {
//			partner = "1401565202";
//			apiclient_certLocation = "apiclient_cert2.p12";
//		}
		//endregion
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		URL url2 = ClientCustomSSL.class.getClassLoader().getResource(apiclient_certLocation);
		URI uri = url2.toURI();
		FileInputStream instream = new FileInputStream(new File(uri));// P12文件目录
		try {
			keyStore.load(instream, partner.toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray())// 这里也是写密码的
				.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {
			HttpPost httpost = new HttpPost(url); // 设置响应头信息
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(data, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();

				String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
				EntityUtils.consume(entity);
				return jsonStr;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

}
