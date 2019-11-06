package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


	//沙箱APPID
	public static final  String app_id = "2016101200670139";
	//沙箱私钥
	public static final  String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRvUyN7GSYMDqgmY8joFSflAoYfsO1jc+PM6JN2ft7BKU0ICRpj8eUAtVgXMfmwGEV9LbX1DEluPv4d7u+fq8ahTolkuvwHZnDj8UEjvqe7BSDFSVlHBrZ17LsiKC9IccZrvuBVIVSPSXHWoM8vWmMmMVtXQZLpH9lfCXglRN5gmaRwY8hU709jb48L9w3VnqoeyjkeK48VfA5Kw+97cHnV9TcFbqOQTUkC1pbl8JEXp/bEeRPq/D+NI9FcKwy3ke+8Z/Eg3eanO/oWr+MMgooSy+EjNxf+TY8Hnw6w8xktDS/t/GX1cc3TKX9zL7saDIj7IbDsr2TmfhrSmIEYYpnAgMBAAECggEAe+QQUBXE7hsRu7TOmdqSFZYjfu9EL/J9n56lOskzn0AzsDwiuBoh7xD5kxFMXjzsS13ckV0w6oB2sqIWV7oZ4NGgvJBJQbXoIPb25QkmxDKsvUIcp/dcXBOhZrI+8ojkWFEIPMk9DMwucWngZqIeOAcmzfFmiKcuJXeEO4Z/J6r+Ndfy/+bSG7iNd8tozISm74fS32Q+yC+UPgFBFCAeeFgF9Cr1FeNS+4nLZFXYc16est+6cGzeWGdfO8uZ2/HWYVZ1unPta6ho6WmA6CO5mxH5gxSlVMEoB54iC2aAvJHoZ0Od2zMRSUSK6rbYhXXXOHFlk7JT+kfyddpPeQHlAQKBgQD9XQ1hFRGXrl0Zl6vTeril/s63wrjCS9ioBtRk0Gh/92YNwWTVqQepTmDgi5cXDURdSKMzyzVMnTE4mX4m9bkt61VgrkbTtADVkMRRl4bwPI+och3NoJzTK3RX3Ys1IM78Fh7u5pDtF8Dr4hBavau0Olher0YeYq4xX1AYtOX9CwKBgQCTQYqsZPP8FPL9hyPdsMqWz/vVYZtRDBE4/Shai3AlJtWzWDlOd7ajXddwVj8Hg+ZFbrVbAwhwmJGRCGMOGadxYB+YjsRS4fN/kneA19OtQytU+yLZ6o+4KXyDwCQYpeL+K9JVVeDOYHAjLFfyY5r7RgdMq3k7cFCW+WRHdQuplQKBgF/39zFmwZsquKkRAkzAl2CI+9QvNbyi7hX3QExPhMWz+DwNnRwxQm2m+psTRM++fr623Nqphi7W++XsfhRTzyjNkzFj0gAuloJOq44cT0iAKAot74wL4ls6gn8ls60UGFL0LhNf0lV8Qd8Xagp0/azuzLpQucaYqlKZP8Wb/+GxAoGAfhXpmjEapCZ/5Ms4z5KX9MgDFrLrnVrayFQjoFuyW21EH7sT3ncsn/czB9IhR9sNaTgg+tu+90H7qhZnUmRu9enbQ/JCccFckEgmIOEsMzfHO42JStPH2WVUYy86+QvZL87v2vecdLkoa0WFzFqzEiHlECXmuRkzOAI0debnZH0CgYB/+enRH6cLfYVIDrQJghWPqQ+v2jFWkUnHCMeDLMJ0mkl8Np9AVCQ64rrIgEwjbg161Ru/k+gg2uONW3kWjXV63sHnu5j9jkoJ9FRwkO9zIWBwcOYfE3hdW87S1Nz+DNhJluCU+maqmkV/nx06YvUeeN9mULbXvGWZ9NiqCxv02Q==";
	//支付宝公钥
	public static final  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkb1MjexkmDA6oJmPI6BUn5QKGH7DtY3PjzOiTdn7ewSlNCAkaY/HlALVYFzH5sBhFfS219QxJbj7+He7vn6vGoU6JZLr8B2Zw4/FBI76nuwUgxUlZRwa2dey7IigvSHHGa77gVSFUj0lx1qDPL1pjJjFbV0GS6R/ZXwl4JUTeYJmkcGPIVO9PY2+PC/cN1Z6qHso5HiuPFXwOSsPve3B51fU3BW6jkE1JAtaW5fCRF6f2xHkT6vw/jSPRXCsMt5HvvGfxIN3mpzv6Fq/jDIKKEsvhIzcX/k2PB58OsPMZLQ0v7fxl9XHN0yl/cy+7GgyI+yGw7K9k5n4a0piBGGKZwIDAQAB";
	//沙箱网关地址
	public static final  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

//	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/xiaomi/pay/notify_url.jsp";

//	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/xiaomi/pay//return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";

	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

