package com.lvfulo.controller.returnmoney.alipay.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lvfulo.utils.ToolUtils;


public class Alipay {
  
  public static String private_key = "";
   
  public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
  
  public static String content_type = "json";
  
  // 字符编码格式 目前支持 gbk 或 utf-8
  public static String input_charset = "GBK";
    
  // 调用的接口名，无需修改
  public static String alipayurl = "https://openapi.alipay.com/gateway.do";
  
  public Alipay(){}
  
  public static String alipayRefundRequest(String out_trade_no, String app_id, double refund_amount){
    
      // 发送请求
      String strResponse = "";
      try {
          if (!ToolUtils.StringIsEmpty(app_id) && app_id.equals("2016101102094331")){
            private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMZ7GCknnufavFsF1anvZ5OV8oz3yW0jDlmaxGqSOpbe0Z51PhSGapj9xDYfxyWwbyALFTZp7qe3wO6NJb8vP6G6DNzpAFXt3uf3chGSt+NPk8w4O0IJ8yPxgX4XV6C+ZHgPe40E34dQe6we/VLGvA/cph/pFE/ALhlUXpSLmVGtAgMBAAECgYA2nQ5T3KwT3hMv9JYLMW3NpVfdRB0vo5LCQxzCukPW2a7SLC6N1UKloEYKIopYq4iYR5RpqZJaoew7d6koRyb5q6/evv+s8y3jH4ee35b7HwGp3CaT13oNPWVYkkaR9sdJfJh55gpiJNNyfUe7bZhhUkiLouQyEldQKIO1sCvjIQJBAPeeJzWdgrHB0duQBPsMsv0dk6KoBHTWAGyEPsPKVuhujpzPvVQmf8SHdSh4Wi0B8kx7KL70gBqigYpsfxXAk8kCQQDNMx9Hmj9wtnx1I1wzpPXykc5e+Gq9Y9NchZLrQB1PwqjtiixpnCp+qG5RW5RB068behxEZ1/qPDcEDXODBtjFAkBpgUWLnrVVMBROzSqSHXMWTkfdgmGDtrsvgZmMquYk2xaA0jMFa/H8twrAV6d0jePDmqZd1qfOsXcol9n+VrOhAkBAb+FOtt1dI7EJh8UYYqDpP2gy4oeCWaqxzzBHt6Z8uYAat90ifOvLQlECM2PnF/ZRBpOB14SDJc2Q9Dhl2OyRAkEA9tnZWT20gBVY5j8tfWUMIMNtMnGs3Jmjk25QW3FCJbEg19Q22utvHpnYixyy/WQqJQt9rqzGLyRwDBNyt7hqDQ==";
          }else if(!ToolUtils.StringIsEmpty(app_id) && app_id.equals("2016101302154610")){
            private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ0kd3h3HZHI1LQ2WeO14g/5sVMgDpX5WSbR07olk2X+ioPyZLhE2cxwIB6N4Skm/pB7hWYUib0WiU9eeNma04mRZk/UvWJF5QHK3oxwMKtU52q+hwCNDPDpoTISNAkj+YOIua9DpSAHC8ZK6yK+A6r6ae7Tld8Jy5FPXaMLEyA9AgMBAAECgYA4szLEbb5mnt9Y0w+mITebag34n2WD6b+oSSerT+macLjMGUcMUhOAN6jvPWRT8/UAbwFYLurwJlSPJdhXNA1FUkT2pxhdtf3Ohmha6BtwWXegGV5/YmhTEOtIs5w306j+04ygbatT9i7/YRw3nLoNkf941iF6wn1jp2qGtlzxIQJBAMwZSYN3zHB7DTlWlElV44RVSJ6cUGmmAOGjFr+IMMgSgpqpUGjl75G57fOeG8q0fmXrf8ILvweemEUQ6yt+SuUCQQDFGll4dhvrWjV6bhIFSmP9ehf7GixP9vMMi+iPuu6FW00NP5qqE4Ql01llN263nnAqY4iGRZOeW/ownE8RrTJ5AkBv74Jw9vgJzILxKnnDSrawMjHUw3gaqS+ckFx+iQvv/q7gcfGmxkzAyI8naFRntNwJu8Hiw5xual2qq1+/B9G9AkBl/ZR+eN9JkYxKpdo9Upka+KqgDJMRbb+mpl7YwOnbp7rfOCJYD3E6AXsonN51tU5lIMvsbuRL9Vwo+zkO9IhBAkEAk9mgwlDFFMQTy4R4pK3/W2nETR/HMQbR1KXc0ayn0cCvG2VDGz0tnafUGaKwdzURlwLi7nh+WeKkQPZvqY2DJw==";
          }
          
          AlipayClient alipayClient = new DefaultAlipayClient(alipayurl,app_id,private_key,content_type,input_charset,ali_public_key);
          AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
          AlipayRefundInfo alidata= new AlipayRefundInfo();
          alidata.setOut_trade_no(out_trade_no);
          alidata.setRefund_amount(refund_amount);
          request.setBizContent(ToolUtils.convertToString(alidata));
          AlipayTradeRefundResponse response = alipayClient.execute(request);
          strResponse=response.getBody();
          if ("10000".equals(response.getCode())) {
              System.out.println("退款成功!" + response.getBody());
          }else {
            System.out.println("退款失败!" + response.getSubMsg());
          }
      } catch (Exception e) {
          
      }
      return strResponse;

  }

}
