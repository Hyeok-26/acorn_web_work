package com.example.spring06.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring06.dto.MemberDto;

//dao 를 bean 으로 만들기 위한 어노테이션
//sqlexception이 발생하면 spring 이 데이터어섹스익셉션(트랜잭션을 관리)을 발생시킨다 
@Repository
public class MemberDaoImpl implements MemberDao{
	
	//MyBatis 기반으로 Db 관련 작업을 하기 위한 핵심 의존객체를 DI 받는다.
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList() {
		/*
		 * SqlSession 객체를 이영해서 회원 목록을 얻어온다.
		 * row 가 여러개이면 selectList("namespace.id");
		 * namespace 란 mapper 파일들 속에서 id로 sql을 골라낸다
		 * select 는 list 를 리턶준다 resulttype은 MemberDto
		 */
		List<MemberDto> list = session.selectList("member.getList");
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		/*
		 * Mapper 의 namespace :member
		 * sql의 id : insert
		 * parameterType : MemberDto
		 */
		
		session.insert("member.insert", dto);
	}

	@Override
	public int update(MemberDto dto) {
		//수정 반영하고 수정반영된 row 의 갯수를 리턴한다(성공이면 1, 실패면 0)
		 return session.update("member.update", dto);
	}

	@Override
	public int delete(int num) {
		//삭제하고 삭제된 row 의 갯수를 리턴한다(성공이면 1, 실패면 0)
		return session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) {
		MemberDto dto = session.selectOne("member.getData", num);

		return dto;
	}

}
