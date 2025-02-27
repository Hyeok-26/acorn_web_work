package com.example.spring10.exception;

//거부된 요청일 떄 발생시킬 Exception 정의하기
public class DeniedException extends RuntimeException{
	public DeniedException(String msg) {
		super(msg);
	}
}
