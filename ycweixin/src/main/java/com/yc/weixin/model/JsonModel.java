package com.yc.weixin.model;

import java.util.List;

public class JsonModel<T> {

	private Integer code;
	private String msg;
	private Object obj;
	private String url;
	private Integer total;
	private Integer pages;
	private Integer pagesize;
	private Integer start;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public JsonModel(Integer code, String msg, Object obj, String url, Integer total, Integer pages, Integer pagesize,
			Integer start, List<T> rows) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
		this.url = url;
		this.total = total;
		this.pages = pages;
		this.pagesize = pagesize;
		this.start = start;
		this.rows = rows;
	}
	private List<T> rows;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj + ", url=" + url + ", total=" + total
				+ ", pages=" + pages + ", pagesize=" + pagesize + ", rows=" + rows + "]";
	}
	
	public JsonModel() {
		super();
	}

}
