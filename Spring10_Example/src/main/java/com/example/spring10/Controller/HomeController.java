package com.example.spring10.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	
	
	@GetMapping("/")
	public String home(Model model) {
		List<String> noticeList = List.of("Spring 프로젝트 시작입니다!",
				"열심히 해보아요","어쩌구...","저쩌구...");
		model.addAttribute("noticeList", noticeList);
		return "home";
	}
}
