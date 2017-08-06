package com.yc.weixin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * 请求微信服务器的校验工具类
 * @author zy
 *
 */
public class SignUtil {
	
	public static final String TOKEN="ycinfo";
	
	public static final String APPID="wx72a20d360dea4bbb";
	
	public static final String APPSECRET="696912240df8aa8577579e5391a26574";
	
	/**
	 *  签名校验:
	 *  1. 将token、timestamp、nonce三个参数进行字典序排序
		2. 将三个参数字符串拼接成一个字符串进行sha1加密
		3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param signature :   签名
	 * @param timestame :   时间戳
	 * @param nonce     : 	随机数
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static boolean checkSignature( String signature, String timestamp, String nonce){
		if(  StringUtil.isBlank(signature) || StringUtil.isBlank(timestamp) || StringUtil.isBlank(nonce) ){
			return false;
		}
		String[] paramArrays=new String[]{TOKEN,timestamp, nonce};
		Arrays.sort(  paramArrays);
		String content=paramArrays[0].concat(  paramArrays[1]  ).concat(   paramArrays[2]);
		String encryptString="";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest=md.digest(    content.toString().getBytes()  );
			encryptString=byteToString( digest );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return encryptString!=null?encryptString.equals(   signature.toUpperCase() ): false;
	}

	private static String byteToString(byte[] digestByte) {
		StringBuffer   sb=new StringBuffer("");
		for(int i=0;i<digestByte.length;i++){
			sb.append(   byteToHexString(digestByte[i])  );
		}
		return sb.toString();
	}

	private static Object byteToHexString(byte b) {
		char[] digit=new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArray=new char[2];
		tempArray[0]=digit[(b>>>4)&0x0F];
		tempArray[1]=digit[b&0x0f];
		
		String s=new String(   tempArray );
		return s;
	}
	
	
}
