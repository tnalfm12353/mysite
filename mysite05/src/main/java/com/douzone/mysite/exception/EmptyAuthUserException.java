package com.douzone.mysite.exception;

public class EmptyAuthUserException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EmptyAuthUserException() {
		super("EmptyAuthUserException : AuthUser is null");
	}
}
