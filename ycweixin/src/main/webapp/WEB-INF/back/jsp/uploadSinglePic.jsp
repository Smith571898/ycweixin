<%@page import="com.yc.weixin.utils.FileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.yc.weixin.utils.*,java.util.*" %>

<%
	FileUpload fu =new FileUpload();
	Map<String ,String>map=fu.uploadFiles(pageContext,request);
	
	//取出回调函数名: jsonp技术
	response.setContentType("text/html;charset=utf-8");
	
	//因为CKEDitor参数是在地址栏中,即get方式传到服务器中的,所以可用HttpServletRequest来接参数
	String callback=request.getParameter("CKEditorFuncNum");
	
	//将结果以客户端指定函数的形似,以javascript代码写到客户端去,这样的客户端的浏览器的js引擎就可以运行
	out.println("<script type=\"text/javascript\">");
	out.println("window.parent.CKEDITOR.tools.callFunction("+callback+",'"+map.get("upload_weburl")+"','')");//相对路径用于显示图片
	out.println("</script>");
	out.flush();


%>