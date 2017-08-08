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
	@Override
	public String toString() {
		return "FollowPushMessage [fid=" + fid + ", ftitle=" + ftitle + ", fpic=" + fpic + ", fcontent=" + fcontent
				+ ", lastmodify=" + lastmodify + ", lastmodifytime=" + lastmodifytime + ", isfollowpush=" + isfollowpush
				+ "]";
	}
	public FollowPushMessage(Integer fid, String ftitle, String fpic, String fcontent, String lastmodify,
			String lastmodifytime, String isfollowpush) {
		super();
		this.fid = fid;
		this.ftitle = ftitle;
		this.fpic = fpic;
		this.fcontent = fcontent;
		this.lastmodify = lastmodify;
		this.lastmodifytime = lastmodifytime;
		this.isfollowpush = isfollowpush;
	}
	public FollowPushMessage() {
		super();
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
