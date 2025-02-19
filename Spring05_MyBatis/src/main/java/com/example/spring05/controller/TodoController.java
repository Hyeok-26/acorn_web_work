package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring05.dto.MemberDto;
import com.example.spring05.dto.TodoDto;
import com.example.spring05.repository.TodoDao;

@Controller
public class TodoController {
	
	@Autowired
	private TodoDao dao;
	
	@GetMapping("/todo/list")
	public String list(Model model) {
		List<TodoDto> list = dao.getList();
		model.addAttribute("list",list);
		return "todo/list";
	}
	//할일 적어놓는 폼
	@GetMapping("/todo/new")
	public String insertForm() {
		return "todo/new";
	}
	//할일 응답
	@PostMapping("/todo/insert")
	public String insert(TodoDto dto) {
		dao.insert(dto);
		return "todo/insert";
	}
	//삭제 요청 응답
	@GetMapping("/todo/delete")
	public String delete(int id) {
		dao.delete(id);
		return "redirect:/todo/list";
	}
	//수정 요청
	@GetMapping("/todo/edit")
	public String edit(int id,Model model) {
		TodoDto dto = dao.getData(id);
		model.addAttribute("dto",dto);
		return "todo/edit";
	}
	//수정 응답
	@PostMapping("/todo/update")
	public String update(TodoDto dto) {
		dao.update(dto);
		return "todo/update";
	}
	
}
