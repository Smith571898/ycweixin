package com.yc.weixin.web.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.weixin.biz.impl.CoreBizImpl;
import com.yc.weixin.service.ChatService;
import com.yc.weixin.utils.SignUtil;


/**
 * 请求处理的核心类
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	
	@Override
	public void init() throws ServletException {
		File indexDir = new File(ChatService.getIndexDir());
		// 如果索引目录不存在则创建索引
		if (!indexDir.exists())
			ChatService.createIndex();
	}
}
