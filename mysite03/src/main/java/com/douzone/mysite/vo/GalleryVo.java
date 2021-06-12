package com.douzone.mysite.vo;

import javax.validation.constraints.NotEmpty;

public class GalleryVo {
	
	private Long id;
	@NotEmpty
	private String comment;
	@NotEmpty
	private String url;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
