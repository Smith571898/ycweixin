package com.yc.weixin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	

	@RequestMapping(value="ToAdminLogin.action")
	public String ToAdminLogin(){
			
		return "AdminLogin";
		
	}

	@RequestMapping(value="AdminLogin.action")
	public String AdminLogin(){
			
		return "main";
		
	}

}
