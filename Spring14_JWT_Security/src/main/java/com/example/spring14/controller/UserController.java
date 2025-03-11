package com.example.spring14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring14.dto.UserDto;
import com.example.spring14.util.JwtUtil;


@Controller
public class UserController {
	
	@Autowired JwtUtil jwtUtil;
	@Autowired AuthenticationManager authManager;
	
	@GetMapping("/api/ping")	//whiteList 에 등록되지 않은 요청은 token이 있어야 요청 가능
	@ResponseBody
	public String ping() {
		return "pong";
	}
	
	@PostMapping("/api/auth")
	@ResponseBody
	public String auth(@RequestBody UserDto dto) throws Exception{
		try{
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
				//인증 매니져 객체를 이용해서 인증 진행
			authManager.authenticate(authToken);
		}catch(Exception e){
			//예외가 발생하면 인증 실패(아이디 혹은 비밀번호 틀림 등등)
			e.printStackTrace();
			throw new RuntimeException();
		}
		//simple claims
		Map<String, Object> claims=Map.of("role","USER", "email","aaa@naver.com");
    	//예외 발생 없이 여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(dto.getUserName(), claims);
		return "Bearer "+token;
	}
	
	//세션 허용갯수 초과시 
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}	
	
	//권한 부족시 or 403 인 경우 
	@RequestMapping("/user/denied")  //GET, POST 등 모두 가능
	public String userDenied() {
		return "user/denied";
	}
	
	//ROLL_STAFF , ROLL_ADMIN 만 요청 가능
	@GetMapping("/staff/user/list")
	public String userList() {
		
		return "user/list";
	}
	//ROLL_ADMIN 만 요청 가능
	@GetMapping("/admin/user/manage")
	public String userManage() {
		
		return "user/manage";
	}
	
	
	@GetMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답 
		return "user/loginform";
	}
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로 
	@GetMapping("/user/required-loginform")
	public String required_loginform() {
		return "user/required-loginform";
	}
	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url 
	@PostMapping("/user/login-success")
	public String loginSuccess() {
		return "user/login-success";
	}
	
	//로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	@PostMapping("/user/login-fail")
	public String loginFail() {
		//로그인 실패임을 알릴 페이지
		return "user/login-fail";
	}
}
