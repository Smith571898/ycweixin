package com.yc.weixin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yc.weixin.model.MediaModel;

public class UpdatePicToQPic {

	public static String update(String content) {
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();
		// String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		// String regEx_img =
		// "(?s)<img.*?(src=\"(.*?((common/FCKeditor.*?)|(cms/simpleDownload\\?fileId=([0-9a-zA-Z]*))))\")";
		p_image = Pattern.compile("(<img[^>]*src=['\"])([^'\"]+)([^>]*>)");
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			String img = m_image.group();
			String url = m_image.group(2);
			String absoluteUrl = CommonUtil.picWithAbsolute.get(url);
			MediaModel mm = MediaUtil.uploadPicFromNewsMaterial(absoluteUrl);
//			MediaModel mm = MediaUtil.uploadPicFromNewsMaterial(url);
			String newUrl = mm.getUrl();
			String newImg = img.replace(url, newUrl);
			content = content.replace(img, newImg);
		}
		
		return content;
	}
}
