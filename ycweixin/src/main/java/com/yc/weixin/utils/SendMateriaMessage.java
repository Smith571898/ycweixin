package com.yc.weixin.utils;

import java.io.IOException;

import com.yc.weixin.model.ErrorModel;
import com.yc.weixin.model.MPNewsModel;
import com.yc.weixin.model.MediaModel;
import com.yc.weixin.model.NewsByTagModel;
import com.yc.weixin.model.TagModel;

public class SendMateriaMessage {
	
	private static final String sendNewsMaterialByTag = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	public static void sendMateriaMessageByTag(MediaModel mm) {
		NewsByTagModel nbtm = new NewsByTagModel();
		TagModel tm = new TagModel();
		MPNewsModel mpnm = new MPNewsModel();
		tm.setIs_to_all(true);
		mpnm.setMedia_id(mm.getMedia_id());
		nbtm.setFilter(tm);
		nbtm.setMpnews(mpnm);
		nbtm.setMsgtype("mpnews");
		nbtm.setSend_ignore_reprint(0);
		
		String sendNewsMaterialByTag1 = sendNewsMaterialByTag.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		
		String jsonStr =  CommonUtil.gson.toJson(nbtm);
		
		String response = null;
		try {
			response = CommonUtil.getResources(sendNewsMaterialByTag1, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ErrorModel errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		
		System.out.println("error:"+errorModel);
	}

	
	

}
