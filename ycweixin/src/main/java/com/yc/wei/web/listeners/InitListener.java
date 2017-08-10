package com.yc.wei.web.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.yc.weixin.biz.UserBiz;


////当servlet容器一启动就会加载这个类
public class InitListener implements  ServletContextListener {
	
	private ApplicationContext  ac;
	public  InitListener(){
	}
	
	
	


	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ServletContext  application=arg0.getServletContext();
		ac=WebApplicationContextUtils.getWebApplicationContext(application);
	
		UserBiz  userBiz=(UserBiz)ac.getBean("userBizImpl");
		
		
	
	}





	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}





	




}
