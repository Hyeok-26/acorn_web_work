package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 클라이언트의 요청을 처리할 컨트롤러를 정의하고 bean 으로 만들기 
 */
@Controller
public class HelloController {
	
	@ResponseBody	//view 페이지를 거치지 않고 클라이언트에게 바로 응답
	@GetMapping("/hello")//클라이언트가 get 방식 "/hello" 경로로 요청을 하면 이 메소드가 실행된다.
	public String hello() {
		
		//String type 을 리턴하면서 메소드에 @ResponseBody 어노테이션을 붙여 놓으면
		// 여깃 리턴할 문자열이 클라이언트에게 그대로 출력된다
		return "Nice to meet you!";
	}
	
	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		return "복권에 당첨될 번호를 꿈꿔요!";
	}
	
}
