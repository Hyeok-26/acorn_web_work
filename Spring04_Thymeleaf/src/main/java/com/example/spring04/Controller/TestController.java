package com.example.spring04.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring04.dto.MemberDto;

@Controller
public class TestController {
	
	@GetMapping("/sequence")
	public String sequence() {
		
		return "test/sequence";
	}
	
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		//db 에서 select 한 결과라고 가정하자
		MemberDto dto1 = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("원숭이").addr("행신동").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("해골").addr("상도동").build();
		
		//read only List
		List<MemberDto> list = List.of(dto1, dto2, dto3);
		
		//해당 데이터를 Model 객체에 담고
		model.addAttribute("list",list);
		//view page 로 이동해서 응답하기
		return "member/list";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		MemberDto dto = MemberDto.builder()
				.num(1)
				.name("김구라")
				.addr("노량진")
				.build();
		//해당 데이터를 Model 객체에 담고
		model.addAttribute("dto",dto);
		//view page 로 이동해서 응답하기
		return "member/info";
	}
	
	@GetMapping("/notice")
	public String notice(Model model) {
		List<String> notice = List.of("Thymeleaf view engine 을 배워 보아요!",
				"하나~!","두울~!","어쩌구...","저쩌구....");
		model.addAttribute("noticeList",notice);
		return "post/notice";
	}
}
