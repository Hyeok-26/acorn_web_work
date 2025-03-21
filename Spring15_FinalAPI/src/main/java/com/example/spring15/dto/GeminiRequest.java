package com.example.spring15.dto;

import java.util.List;

import lombok.Data;
/*
 * {
       "contents": [
   			{
    			"parts":[{"text": "Explain how AI works"}]
    		}
    	]
   } 
   이런 형식을 만들기 위한 클래스
 */
@Data
public class GeminiRequest {
	private List<Content> contents;
	
	@Data
	public static class Content {
		private List<Part> parts;
	}
	
	@Data
	public static class Part{
		private String text;
	}
}
