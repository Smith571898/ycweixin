package com.yc.weixin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	

	@RequestMapping(value="ToAdminLogin.action")
	public String ToAdminLogin(){
			
		return "AdminLogin";
		
	}

	@RequestMapping(value="AdminLogin.action")
	public String AdminLogin(){
			
		return "main";
		
	}
	@RequestMapping(value="touploadTempMaterial.action")
	public String touploadTempMaterial(){
			
		return "uploadTempMaterial";
		
	}
	@RequestMapping(value="touploadpicbat.action")
	public String touploadbat(){
			
		return "uploadpicbat";
		
	}
	@RequestMapping(value="touploadfilebat.action")
	public String touploadfilebat(){
			
		return "uploadfilebat";
		
	}
	
	@RequestMapping(value="showmenu.action")
	public String showmenu(){
			
		return "showmenu";
		
	}
	
	@RequestMapping(value="moniweixin.action")
	public String moni(){
			
		return "moniweixin";
		
	}
	@RequestMapping(value="addmenu.action")
	public String addmenu(){
			
		return "addmenu";
		
	}
	@RequestMapping(value="findmenu.action")
	public String findmenu(){
			
		return "findmenu";
		
	}
}
