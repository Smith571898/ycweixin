package com.yc.weixin.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.weixin.bean.Admin;
import com.yc.weixin.biz.AdminBiz;
import com.yc.weixin.model.JsonModel;

@RestController
public class AdminController {
	
	@Resource(name="adminBizImpl")
	private AdminBiz  adminBiz;
	@RequestMapping(value="doAdminLogin.action")
	public JsonModel toMain(Admin admin,HttpServletRequest request,HttpSession session){
		
		
		System.out.println(1);
		JsonModel jsonModel=new JsonModel();
		jsonModel.setCode(1);
		/*String username =request.getParameter("adminpwd");
		Admin admin2=adminBiz.AdminLogin(admin);
		try {
			if(admin2!=null){
				session.setAttribute("Admin", admin2);
			jsonModel.setCode(1);
			
			}else{
				jsonModel.setCode(0);
				jsonModel.setMsg("登录失败");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}*/
		return jsonModel;
		}
	
	@RequestMapping(value="xiaochengxu.action")
	public void toMain11(HttpServletRequest request,HttpSession session){
		

		System.out.println(1);
		}
}
