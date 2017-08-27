package com.yc.weixin.model;

public class MediaModel {
	//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
	private String type;
	//视频id
	private String thumb_media_id;
	//媒体文件id
	private String media_id;
	//创建时间
	private long created_at;
	//上传图片的url
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "MediaModel [type=" + type + ", thumb_media_id=" + thumb_media_id + ", media_id=" + media_id
				+ ", created_at=" + created_at + ", url=" + url + "]";
	}
}
