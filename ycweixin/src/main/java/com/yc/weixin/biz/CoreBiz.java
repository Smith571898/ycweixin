package com.yc.weixin.biz;

import javax.servlet.http.HttpServletRequest;

public interface CoreBiz {
	
	/**
	 * 解析从微信端传输过来的消息
	 * @param req
	 * @return
	 */
	public String processXml(HttpServletRequest req);

}
