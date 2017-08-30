package com.yc.weixin.bean;

import java.io.Serializable;

public class FollowPushMessage implements  Serializable {

	private static final long serialVersionUID = -3213260787005913280L;
	private Integer fid;
	private String  ftitle ;
	private String	fpic  ;
	private String	fcontent ;
	private String	lastmodify ;
	private String	lastmodifytime ;
	private String	isfollowpush ;
	private String  media_id;
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getFpic() {
		return fpic;
	}
	public void setFpic(String fpic) {
		this.fpic = fpic;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getLastmodify() {
		return lastmodify;
	}
	public void setLastmodify(String lastmodify) {
		this.lastmodify = lastmodify;
	}
	public String getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(String lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	public String getIsfollowpush() {
		return isfollowpush;
	}
	public void setIsfollowpush(String isfollowpush) {
		this.isfollowpush = isfollowpush;
	}
	
	
	

}
