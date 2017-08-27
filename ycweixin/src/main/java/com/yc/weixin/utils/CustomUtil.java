package com.yc.weixin.utils;

import java.io.IOException;

import com.yc.weixin.model.ErrorModel;
import com.yc.weixin.model.KFGroupModel;
import com.yc.weixin.model.KFModel;
import com.yc.weixin.model.KFOnlineGroupModel;

//发送客服信息
public class CustomUtil {

	// 查看所有客服信息
	private static final String getkflistInfoUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";

	// 查看所有在线客服信息
	private static final String getonlinekflistInfoUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN";

	// 添加客服账号信息
	private static final String addkfInfoUrl = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";

	// 邀请绑定客服帐号
	private static final String inviteworkerUrl = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=ACCESS_TOKEN";

	// 设置客服信息
	private static final String uppdatekfInfoUrl = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";

	// 设置客服头像
	private static final String updatekfheadimgUrl = "https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";

	// 删除客服账号
	private static final String delkfInfoUrl = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";

	// 查看所有客服信息
	public static KFGroupModel getkflistInfo() {
		KFGroupModel kfGroupModel = null;

		String getkflistInfoUrl1 = getkflistInfoUrl;
		getkflistInfoUrl1 = getkflistInfoUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		try {
			String response = CommonUtil.getResources(getkflistInfoUrl1, null);
			kfGroupModel = CommonUtil.gson.fromJson(response, KFGroupModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return kfGroupModel;
	}

	// 查看所有在线客服信息
	public static KFOnlineGroupModel getonlinekflistInfo() {
		KFOnlineGroupModel kfOnlineGroup = null;

		String getonlinekflistInfoUrl1 = getonlinekflistInfoUrl;
		getonlinekflistInfoUrl1 = getonlinekflistInfoUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		try {
			String response = CommonUtil.getResources(getonlinekflistInfoUrl1, null);
			kfOnlineGroup = CommonUtil.gson.fromJson(response, KFOnlineGroupModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return kfOnlineGroup;
	}

	// 添加客服账号信息
	public static ErrorModel addkfInfo(KFModel kfModel) {
		ErrorModel errorModel = null;

		String addkfInfoUrl1 = addkfInfoUrl;
		addkfInfoUrl1 = addkfInfoUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		String jsonStr = CommonUtil.gson.toJson(kfModel);

		try {
			String response = CommonUtil.getResources(addkfInfoUrl1, jsonStr);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return errorModel;
	}

	// 邀请绑定客服帐号
	public static ErrorModel inviteworker(KFModel kfModel) {
		ErrorModel errorModel = null;

		String inviteworkerUrl1 = inviteworkerUrl;
		inviteworkerUrl1 = inviteworkerUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		String jsonStr = CommonUtil.gson.toJson(kfModel);

		try {
			String response = CommonUtil.getResources(inviteworkerUrl1, jsonStr);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return errorModel;
	}

	// 设置客服信息
	public static ErrorModel uppdatekfInfo(KFModel kfModel) {
		ErrorModel errorModel = null;

		String uppdatekfInfoUrl1 = uppdatekfInfoUrl;
		uppdatekfInfoUrl1 = uppdatekfInfoUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		String jsonStr = CommonUtil.gson.toJson(kfModel);

		try {
			String response = CommonUtil.getResources(uppdatekfInfoUrl1, jsonStr);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return errorModel;
	}

	// 设置客服头像
	public static ErrorModel updatekfheadimg(KFModel kfModel, String headimgUrl) {
		ErrorModel errorModel = null;

		String updatekfheadimgUrl1 = updatekfheadimgUrl;
		updatekfheadimgUrl1 = updatekfheadimgUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		updatekfheadimgUrl1 = updatekfheadimgUrl1.replace("KFACCOUNT", kfModel.getKf_account());

		try {
			String response = CommonUtil.getMediaResource(updatekfheadimgUrl1, headimgUrl);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return errorModel;
	}

	// 删除客服账号
	public static ErrorModel delkfInfo(KFModel kfModel) {
		ErrorModel errorModel = null;

		String delkfInfoUrl1 = delkfInfoUrl;
		delkfInfoUrl1 = delkfInfoUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		delkfInfoUrl1 = delkfInfoUrl1.replace("KFACCOUNT", kfModel.getKf_account());

		try {
			String response = CommonUtil.getResources(delkfInfoUrl1,null);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return errorModel;
	}
}
