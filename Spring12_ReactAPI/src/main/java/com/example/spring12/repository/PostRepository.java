package com.example.spring12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring12.entity.Post;
/*
 * JpaRepository<Entity 클래스 Type, id 역할을 하는 칼럼의 Type>
 * 
 * 	위와 같이 설정을 하면
 * PostRepository 의 구현 클래스가 자동으로 만들어진고
 * 해당 클래스로 생성된 객체가 bean 으로 관리된다.
 * 
 */
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
