<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>  
 <head>  
  <title> New Document </title>  
  <%
String path = request.getContextPath();   // /bbs
//http ://                         localhost     :      8080                /bbs/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">

  <link rel="stylesheet" type="text/css" href="css/menu.css" media="all">  
 </head>  
  
 <body>  
                                                   
 </body>  
</html>  