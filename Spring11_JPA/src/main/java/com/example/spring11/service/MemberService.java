package com.example.spring11.service;

import java.util.List;
import java.util.Optional;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;

public interface MemberService {
	public List<MemberDto> getAll();
	public void saveMember(MemberDto dto);
	public void editMember(MemberDto dto);
	public void deleteMember(int num);
	public MemberDto getMember(int num); 
}
