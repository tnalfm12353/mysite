package com.douzone.mysite.exception;

public class GuestbookRepositoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GuestbookRepositoryException() {
		super("GuestbookRepositoryException Occur");
	}
	
	public GuestbookRepositoryException(String msg) {
		super(msg);
	}
}
