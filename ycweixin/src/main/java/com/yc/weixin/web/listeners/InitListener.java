package com.yc.weixin.web.listeners;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.model.UserModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.UserInfoUtil;


////当servlet容器一启动就会加载这个类
public class InitListener implements  ServletContextListener {
	
	
	
	public  InitListener(){
	}
	
	
	


	public void contextInitialized(ServletContextEvent arg0) {
	
		ServletContext  application=arg0.getServletContext();
		
	
		
		 
			
		
	
	}





	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}





	




}
