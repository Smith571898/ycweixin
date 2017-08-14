package com.yc.weixin.web.listeners;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.biz.impl.UserBizImpl;
import com.yc.weixin.model.UserModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.UserInfoUtil;


public class ReqInitListener implements ServletRequestListener{

	

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	
		
	}

}
