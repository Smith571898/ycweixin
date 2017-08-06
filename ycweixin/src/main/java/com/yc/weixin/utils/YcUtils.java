package com.yc.weixin.utils;

import org.apache.log4j.Logger;

public class YcUtils {
	
	public static Logger logger=Logger.getLogger(YcUtils.class);
	
	public static void debug( Exception e){
		StringBuffer sb=new StringBuffer();
		for( StackTraceElement ste: e.getStackTrace()){
			sb.append(    ste.toString()+"\n");
		}
		logger.debug(  e.getMessage()+"\n"+  sb.toString() );
	}
	
	public static void debug( String str){
			logger.debug(   str );
	}
	
	public static void error( Exception e){
		StringBuffer sb=new StringBuffer();
		for( StackTraceElement ste: e.getStackTrace()){
			sb.append(    ste.toString()+"\n");
		}
		logger.error(   e.getMessage()+"\n"+ sb.toString() );
	}
	
	public static void error( String s){
			logger.error(   s );
	}
	
	public static void info( Exception e){
		StringBuffer sb=new StringBuffer();
		for( StackTraceElement ste: e.getStackTrace()){
			sb.append(    ste.toString()+"\n");
		}
		logger.info(   e.getMessage()+"\n"+ sb.toString() );
	}
	
	public static void info( String s){
			logger.info(    s );
	}
}
