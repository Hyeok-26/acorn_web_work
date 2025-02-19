package com.example.spring06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.repository.MemberDao;

//서비스 클래스에는 @Service 어노테이션을 붙인다.
@Service
public class MemberServiceImpl implements MemberService{
	//서비스 객체는 Controller 에서 활용하는 유틸리티라고 생각하면 된다
	@Autowired
	private MemberDao dao;
	
	@Override
	public List<MemberDto> findAll() {
		//회원목록을 얻어와서 리턴한다.
		List<MemberDto> list = dao.getList();
		return list;
	}

	@Override
	public MemberDto findById(int num) {
		//번호를 이용하여 회원 1명의 정보를 얻어와서 리턴
		MemberDto dto=dao.getData(num);
		return dto;
	}

	@Override
	public void save(MemberDto dto) {
		//try {
			//dao에서 에외가 발생하면 작업 실패이다.
			dao.insert(dto);
		//}catch(Exception e) {
			//예외정보를 콘솔창에 출력해
			//e.printStackTrace();
			//예외를 직접 발생시킨다
			//throw new RuntimeException("회원정보 처리중 에러가 발생했습니다");
		//}
	}

	@Override
	public void update(MemberDto dto) {
		//dao 에서 sqlexception이 발생하는 경우
			int rowCount = dao.update(dto);
			//만일 수정된 row의 갯수가 0이면
			//rowCount 가 0 이여서 RuntimeException 이 발생하는 경우
			if(rowCount == 0){
				throw new RuntimeException("수정할 회원의 정보가 없습니다");
			}			
	}

	@Override
	public void deleteById(int num) {
		
			int rowCount = dao.delete(num);
			
			if(rowCount == 0){
				throw new RuntimeException("삭제할 회원이 존재 하지 않습니다");
			}
	}	

}
