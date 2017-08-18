package com.yc.weixin.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.biz.MessageBiz;
import com.yc.weixin.model.JsonModel;

@RestController
public class MessageController {
	@Resource(name = "messageBizImpl")
	private MessageBiz messageBiz;

	@RequestMapping(value = "FollowPush.action")
	public ModelAndView FollowPushPage() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("FollowPush");
		return mav;
	}

	@RequestMapping(value = "findFollowPushMessage.action", produces = "text/html;charset=UTF-8")
	public String findAllUserInfo(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start = (pages - 1) * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		List<FollowPushMessage> list = messageBiz.findFollowPushMessage(map);
		Integer total = messageBiz.finFollowPushCount();
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	@RequestMapping(value = "toeditor.action")
	public ModelAndView toEditor(HttpSession session, @RequestParam(value = "ftitle") String ftitle,
			@RequestParam(value = "fcontent") String fcontent,
			@RequestParam(value = "isfollowpush") String isfollowpush, @RequestParam(value = "fpic") String fpic,
			@RequestParam(value = "fid") String fid) {
		ModelAndView mav = new ModelAndView();
		session.setAttribute("ftitle", ftitle);
		session.setAttribute("fcontent", fcontent);
		session.setAttribute("isfollowpush", isfollowpush);
		session.setAttribute("fpic", fpic);
		session.setAttribute("fid", fid);
		System.out.println(ftitle);
		System.out.println(fcontent);
		System.out.println(isfollowpush);
		System.out.println(fpic);
		System.out.println(fid);
		mav.setViewName("editor");
		return mav;
	}

	/*@RequestMapping(value = "touploadSinglePic.action")
	public ModelAndView topicupload() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("uploadSinglePic");
		return mav;
	}*/
	
	@RequestMapping(value = "uploadImg.action")
	public void dopicupload(@RequestParam("upload") MultipartFile file,HttpServletRequest request, HttpSession session,HttpServletResponse response) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {

			String tomcatwebroot = request.getServletContext().getRealPath("/");// ycweixin路径E:\apache-tomcat-8.0.44\webapps\ycweixin\
			File tomcat = new File(tomcatwebroot);// E:\apache-tomcat-8.0.44\webapps\ycweixin\
			File real = tomcat.getParentFile();// E:\apache-tomcat-8.0.44\webapps

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmsss");
			String prefix = df.format(new Date());// 201708101526049
			String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// .jpg
			String filename = prefix + fileType;// 201708181404047.jpg
			String path = real + "\\tuwen/";// E:\apache-tomcat-8.0.44\webapps\tuwen/
			String web_url="../tuwen/";
			File newFile = new File(path);//E:\apache-tomcat-8.0.44\webapps\tuwen
			if (!newFile.exists()) {
				newFile.mkdir();
			}
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			path += filename;
			web_url+=filename;
			File newFile1 = new File(path);
			file.transferTo(newFile1);

			//因为CKEDitor参数是在地址栏中,即get方式传到服务器中的,所以可用HttpServletRequest来接参数
			String callback=request.getParameter("CKEditorFuncNum");
			PrintWriter out =response.getWriter();
			//将结果以客户端指定函数的形似,以javascript代码写到客户端去,这样的客户端的浏览器的js引擎就可以运行
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction("+callback+",'"+web_url+"','')");//相对路径用于显示图片
			out.println("</script>");
			out.flush();

		} else {
		System.out.println("error");
		}

	}

	@RequestMapping(value = "doupdateFollowPush.action", method = RequestMethod.POST)
	private ModelAndView fildUpload(@RequestParam(value = "fpic", required = false) MultipartFile file,
			HttpServletRequest request, HttpSession session) throws Exception {
		FollowPushMessage fpm = new FollowPushMessage();
		if (!file.isEmpty()) {

			String tomcatwebroot = request.getServletContext().getRealPath("/");// ycweixin路径E:\apache-tomcat-8.0.44\webapps\ycweixin\
			File tomcat = new File(tomcatwebroot);// E:\apache-tomcat-8.0.44\webapps\ycweixin
			File real = tomcat.getParentFile();// E:\apache-tomcat-8.0.44\webapps

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmsss");
			String prefix = df.format(new Date());// 201708101526049
			String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// .jpg
			String filename = prefix + fileType;// E:\apache-tomcat-8.0.44\webapps
			String path = real + "\\images/";

			File newFile = new File(path);
			if (!newFile.exists()) {
				newFile.mkdir();
			}
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			path += filename;
			File newFile1 = new File(path);
			file.transferTo(newFile1);
			fpm.setFpic(filename);
		} else {
			fpm.setFpic("");
		}

		// 基本表单

		if(session.getAttribute("fid")!=null){
			fpm.setFid(Integer.valueOf(session.getAttribute("fid").toString()));
		}
		fpm.setFtitle(request.getParameter("ftitle"));
		fpm.setFcontent(request.getParameter("fcontent"));
		fpm.setIsfollowpush(request.getParameter("ss"));
		fpm.setLastmodifytime(String.valueOf(System.currentTimeMillis()));
		fpm.setLastmodify("admin");
		System.out.println(request.getParameter("ftitle"));
		ModelAndView mav = new ModelAndView();
		if (messageBiz.updateFollowPush(fpm)) {
			mav.setViewName("success");
		} else {
			mav.setViewName("faile");
		}

		return mav;
	}

}
