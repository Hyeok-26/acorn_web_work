package com.example.spring11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring11.entity.Member;
/*
 * 아래ㅗ아같이 선언만 해도 MemberRepository 인터페이스를 구현한 클래슬 생성된 객체가
 * bena으로 관리된디
 */
//extends JpaRepository<Entity type, Entity에서 아이디 역할을 하는 필드의 type> 
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	
	
}
