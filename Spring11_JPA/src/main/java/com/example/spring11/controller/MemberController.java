package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.service.MemberService;

import jakarta.annotation.PostConstruct;

@Controller
public class MemberController {
	
	@Autowired private MemberService service;
	
	@PostMapping("/member/save")
	public String save(MemberDto dto) {
		service.saveMember(dto);
		
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/new")
	public String memberNew() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		List<MemberDto> list = service.getAll();
		model.addAttribute("list", list);
		
		return "member/list";
	}
	
	@GetMapping("/member/edit")
	public String edit(Integer num,Model model) {
		MemberDto dto = service.getMember(num);
		model.addAttribute("dto", dto);
		return "member/edit";
	}
	
	@GetMapping("/member/delete")
	public String deleteMember(int num) {
		service.deleteMember(num);
		return "redirect:/member/list";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		service.editMember(dto);
        return "redirect:/member/list";
	} 
}
