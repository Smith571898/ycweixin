package com.yc.weixin.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.weixin.utils.AccessTokenUtil;

@Controller
public class IndexController {
	
	@RequestMapping(value="ToAdminLogin.action")
	public String ToAdminLogin(){
		return "admin/AdminLogin";
	}

	@RequestMapping(value="toMain.action")
	public String AdminLogin(){
		return "main";
	}
	
	@RequestMapping(value="touploadTempMaterial.action")
	public String touploadTempMaterial(){
		return "material/uploadTempMaterial";
	}
	
	@RequestMapping(value="touploadForeverMaterial.action")
	public String touploadForeverMaterial(){
		return "material/uploadForeverMaterial";
	}
	
	/**
	 * 上传临时图片的action   
	 * @return
	 */
	@RequestMapping(value="touploadTemppicbat.action")
	public String touploadbat(HttpSession session){
		return "material/uploadtemppic";
	}
	
	/**
	 * 上传永久图片素材的action   
	 * @return
	 */
	@RequestMapping(value="touploadforeverpic.action")
	public String touploadbat1(HttpSession session){
		return "material/uploadforeverpic";
		
	}
	
	/**
	 * 
	 * 上传临时视频页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadTempvideobat.action")
	public String touploadtempvediobat(HttpSession session){
		//	session.setAttribute("videoaction", "douploadTempVideobat.action");//把上传临时视频的action  存session  加载uploadvediobat页面的时候注入
		return "material/uploadtempvideo";
		
	}
	
	/**
	 * 
	 * 上传永久视频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadForevervideo.action")
	public String touploadforevervediobat(HttpSession session){
		//	session.setAttribute("videoaction", "douploadForeverVideobat.action");//把上传永久视频的action  存session  加载uploadvediobat页面的时候注入
		return "material/uploadforevervideo";
		
	}
	
	
	/**
	 * 
	 * 上传临时音频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadTempaudiobat.action")
	public String touploadtempaudiobat(HttpSession session){
		//	session.setAttribute("audioaction", "douploadTempAudiobat.action");//把上传临时音频的action  存session  加载uploadvediobat页面的时候注入
		return "material/uploadtempaudio";
		
	}
	
	/**
	 * 
	 * 上传永久音频素材页面加载时自动加载三个选项卡里面的ifram页面
	 * @return
	 */
	@RequestMapping(value="touploadForeveraudio.action")
	public String touploadforeveraudiobat(HttpSession session){
		//	session.setAttribute("audioaction", "douploadForeverAudiobat.action");//把上传永久音频的action  存session  加载uploadvediobat页面的时候注入
		return "material/uploadforeveraudio";
	}
	
	@RequestMapping(value="findmenu.action")
	public String findmenu(){
			
		return "menu/findmenu";
		
	}

	@RequestMapping(path="toAddNewsMateria.action")
	public String toAddNewsMateria(){
		return "material/addNewsMateria";
	}
	
	@RequestMapping(path="toAddFollowPush.action")
	public String toAddFollowPush(){
		return "followpush/addFollowPush";
	}
	
	@RequestMapping(value="toChatLog.action")
	public String toChatLog(){
		
		return "robot/ChatLog";
	}
	
	/**
	 * 
	 * 跳转到机器人问题回复设置界面
	 * @return
	 */
	@RequestMapping(value="toRobotReplyManager.action")
	public String toRobotReplyManager(){
		
		return "robot/RobotReplyManager";
	}
	
	@RequestMapping(path="toFindMaterial.action")
	public String toFindMaterial(){
		return "material/findMaterial";
	}
	
	@RequestMapping(path="toFindNews.action")
	public String toFindNews(){
		return "material/findNews";
	}
}
