package com.yc.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 网络编程帮助类 <br />
 * @author zy   <br />
 *
 */
public class YcNetUtil {
	
	public static String urlEncodeUtf8( String source){
		String result=source;
		try {
			result=URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
     * 访问网络资源，返回字符串。</br>
      * @param   requestUrl     目标资源地址
     * @return 目标资源的字符串
	 * @throws Exception 
     */
    public static String httpRequest(String requestUrl) throws Exception {  
        StringBuffer buffer = null;  
        BufferedReader bufferedReader=null;
        HttpURLConnection httpUrlConn=null;
        try {  
            // 建立连接  
            URL url = new URL(requestUrl);  
            httpUrlConn= (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            // 获取输入流  
             bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8") );  
            // 读取返回结果  
            buffer = new StringBuffer();  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
           
        } catch (Exception e) {  
            YcUtils.error(e);
            throw e;
        } finally{
        	 // 释放资源    
        	if(   bufferedReader!=null){
        		try {
					bufferedReader.close();
				} catch (IOException e) {
					YcUtils.error(e);
		            throw e;
				}
        	}
        	httpUrlConn.disconnect();  
        } 
        return buffer.toString();  
    }  
}
