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
		//List<Member> entityList = repo.findAll();
		
		//추가된 메소드를 이용해서 num 에 대해서 내림차순 정력된 목록을 얻어낼 수 잇다
		List<Member> entityList = repo.findAllByOrderByNumDesc();
		
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
		// stream() 을 활용하여 한줄의 coding 으로 위의 동작을 할 수 있다.
		List<MemberDto> list = entityList.stream()
                .map(MemberDto::toDto)
                .toList();
		return list;
	}

	@Override
	public void saveMember(MemberDto dto) {
		repo.save(Member.toEntity(dto));
	}

	@Override
	public void editMember(MemberDto dto) {
		//MemberDto 를 Entity 로 변경해서 save 한다
		repo.save(Member.toEntity(dto));
		
	}

	@Override
	public void deleteMember(int num) {
		repo.deleteById(num);
	}

	@Override
	public MemberDto getMember(int num) {
		//id 를 이용해서 member entity type 을 얻어낸다
		Member member = repo.findById(num).get();
		//entity 를 dto 로 변경해서 리턴
		return MemberDto.toDto(member);
	}

}
