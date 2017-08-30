package com.yc.weixin.web.listeners;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.weixin.biz.ChatBiz;

////当servlet容器一启动就会加载这个类
public class InitListener implements ServletContextListener {
	private ApplicationContext ac;


	public InitListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		ac = WebApplicationContextUtils.getWebApplicationContext(application);
		ChatBiz chatBiz = (ChatBiz) ac.getBean("chatBizImpl");
		File indexDir = new File(chatBiz.getIndexDir());
		// 如果索引目录不存在则创建索引
		if (!indexDir.exists()) {
			chatBiz.createIndex();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
