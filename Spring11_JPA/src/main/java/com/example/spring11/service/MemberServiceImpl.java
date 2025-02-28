package com.example.spring11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	//jpa 가 관리하는 데이터 저장소
	@Autowired private MemberRepository repo;
	
	@Override
	public List<MemberDto> getAll() {
		//Member Entity 의 목록
		List<Member> entityList = repo.findAll();
		/*
		//MemberDto 의 목록으로 만들어서 리턴해야 한다.
		List<MemberDto> list = new ArrayList<>();
		//반복문 돌면서 Member 객체를 순서대로 참조
		for(Member tmp : entityList) {
			list.add(MemberDto.toDto(tmp));
		}
		*/
		//한줄코딩 유행
		//List<MemberDto> list = entityList.stream().map(item -> MemberDto.toDto(item)).toList();
		List<MemberDto> list = entityList.stream()
                .map(MemberDto::toDto)
                .toList();
		return list;
	}

	@Override
	public void saveMember(MemberDto dto) {
		repo.save(MemberDto.toEntity(dto));
	}

	@Override
	public void editMember(MemberDto dto) {
		repo.save(MemberDto.toEntity(dto));
		
	}

	@Override
	public void deleteMember(Integer num) {
		repo.deleteById(num);
	}

	@Override
	public MemberDto getMember(Integer num) {
		repo.getById(num);
		return ;
	}

}
