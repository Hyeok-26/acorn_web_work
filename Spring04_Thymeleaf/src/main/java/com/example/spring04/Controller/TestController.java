package com.example.spring04.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring04.dto.MemberDto;

@Controller
public class TestController {
	
	@GetMapping("/unescape")
	public String unescape(Model model) {
		//html 형식의 문자열을 template 페이지에 전달할 일도 발생한다
		String html="""
			<ul>
				<li>하나</li>
				<li>두울</li>
			</ul>	
		""";
		model.addAttribute("content",html);
		return "test/unescape";
	}
	
	@GetMapping("/include")
	public String include(Model model) {
		
		model.addAttribute("title","오늘의 인사");
		model.addAttribute("content","또 만났군요!");
		return "test/include";
	}
	
	
	@GetMapping("/javascript")
	public String javascript(Model model) {
		//로그인 여부
		model.addAttribute("isLogin",false);
		//나이
		model.addAttribute("age",15);
		//이름
		model.addAttribute("name","김구라");
		
		//회원 한명의 정보
		MemberDto dto = MemberDto.builder()
				.num(1)
				.name("김구라")
				.addr("노량진")
				.build();
		//해당 데이터를 Model 객체에 담고
		model.addAttribute("dto",dto);
		
		//db 에서 select 한 결과라고 가정하자
		MemberDto dto1 = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("원숭이").addr("행신동").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("해골").addr("상도동").build();
				
		//read only List
		List<MemberDto> list = List.of(dto1, dto2, dto3);
				
		//해당 데이터를 Model 객체에 담고
		model.addAttribute("list",list);
		
		return "test/javascript";
	}
	
	//조건부 렌더링
	@GetMapping("/if")
	public String ifTest(Model model) {
		
		//로그인 여부
		model.addAttribute("isLogin",true);
		//나이
		model.addAttribute("age",30);
		//점수
		model.addAttribute("jumsu",80);
		//role
		model.addAttribute("role","admin");
		
		return "test/if";
	}
	
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
