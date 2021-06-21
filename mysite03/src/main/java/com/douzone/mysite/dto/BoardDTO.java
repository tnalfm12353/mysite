package com.douzone.mysite.dto;

public class BoardDTO {

	private int page = 1;
	private String kwd = "";

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getKwd() {
		return kwd;
	}
	public void setKwd(String kwd) {
		this.kwd = kwd;
	}
	@Override
	public String toString() {
		return "BoardDTO [page=" + page + ", kwd=" + kwd + "]";
	}
}
