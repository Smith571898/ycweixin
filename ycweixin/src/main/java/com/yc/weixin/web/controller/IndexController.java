package com.yc.weixin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.weixin.bean.Menu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.utils.AccessTokenUtil;

@Controller
public class IndexController {
	
	@Resource(name="menuBizImpl")
	private MenuBiz menuBiz;

	@RequestMapping(value="ToAdminLogin.action")
	public String ToAdminLogin(){
			
		return "AdminLogin";
		
	}

	@RequestMapping(value="toMain.action")
	public String AdminLogin(){
			
		return "main";
		
	}
	@RequestMapping(value="touploadTempMaterial.action")
	public String touploadTempMaterial(){
			
		return "uploadTempMaterial";
		
	}
	@RequestMapping(value="touploadForeverMaterial.action")
	public String touploadForeverMaterial(){
			
		return "uploadForeverMaterial";
		
	}
	/**
	 * 上传临时图片的action   
	 * @return
	 */
	@RequestMapping(value="touploadTemppicbat.action")
	public String touploadbat(HttpSession session){
		return "uploadtemppic";
	}
	
	/**
	 * 上传永久图片素材的action   
	 * @return
	 */
	@RequestMapping(value="touploadforeverpic.action")
	public String touploadbat1(HttpSession session){
		return "uploadforeverpic";
		
	}
	
	

	
	
	
	/**
	 * 
	 * 上传临时视频页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadTempvideobat.action")
	public String touploadtempvediobat(HttpSession session){
		//	session.setAttribute("videoaction", "douploadTempVideobat.action");//把上传临时视频的action  存session  加载uploadvediobat页面的时候注入
		return "uploadtempvideo";
		
	}
	
	/**
	 * 
	 * 上传永久视频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadForevervideo.action")
	public String touploadforevervediobat(HttpSession session){
		//	session.setAttribute("videoaction", "douploadForeverVideobat.action");//把上传永久视频的action  存session  加载uploadvediobat页面的时候注入
		return "uploadforevervideo";
		
	}
	
	
	/**
	 * 
	 * 上传临时音频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadTempaudiobat.action")
	public String touploadtempaudiobat(HttpSession session){
		//	session.setAttribute("audioaction", "douploadTempAudiobat.action");//把上传临时音频的action  存session  加载uploadvediobat页面的时候注入
		return "uploadtempaudio";
		
	}
	
	/**
	 * 
	 * 上传永久音频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadForeveraudio.action")
	public String touploadforeveraudiobat(HttpSession session){
		//	session.setAttribute("audioaction", "douploadForeverAudiobat.action");//把上传永久音频的action  存session  加载uploadvediobat页面的时候注入
		return "uploadforeveraudio";
		
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
	public String findmenu(HttpSession session){
			
	
		return "findmenu";
		
	}

	@RequestMapping(value="goOrderMenu.action")
	public String goOrderMenu(){
			
		return "ordermenu";
		
	}

	
	@RequestMapping(path="toAddFollowPush.action")
	public String toAddFollowPush(){
		return "addFollowPush";
	}
	
	@RequestMapping(value="toChatLog.action")
	public String toChatLog(){
		
		return "ChatLog";
	}
	
	
	/**
	 * 
	 * 跳转到机器人问题回复设置界面
	 * @return
	 */
	@RequestMapping(value="toRobotReplyManager.action")
	public String toRobotReplyManager(){
		
		return "RobotReplyManager";
	}
}
