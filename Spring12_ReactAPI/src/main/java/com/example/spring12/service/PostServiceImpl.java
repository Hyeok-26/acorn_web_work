package com.example.spring12.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	//JpaRepository 객체를 직접 주입 받느다
	@Autowired private PostRepository repo;
	
	@Override
	public List<PostDto> findAll() {
		//Entity List 를 얻어낸다
		List<Post> list = repo.findAll();
		//dto의 List 의 리스트로 변경해서 리턴
		return list.stream().map(PostDto::toDto).toList();
	}

	@Override
	public PostDto save(PostDto dto) {
		//Entity 로 변경해서 저장하고 걸과를 Entity 로 리턴받는다
		Post post = repo.save(Post.toEntity(dto));
		//결과 Entity 를 dto 로 변경해서 리턴받는다.
		return PostDto.toDto(post);
	}

	@Override
	public PostDto delete(long id) {
		//삭제할 Entity를 미리 얻어낸다(없으면 에외가 발색하고 있으면 Post 가 리턴된다)
		//Post post = repo.findById(id).orElseThrow();
		
		//만일 원하눈 예외를 발생시키고 싶으면(리턴한 RunTimeException 이 자동으로 throw 된다(람다 함수 사용)) 
		Post post = repo.findById(id).orElseThrow(()->new RuntimeException("post not found!"));
		//삭제 작업을 하고
		repo.deleteById(id);
		//삭제한 Entity 를 dto 로 변경해서 리턴
		return PostDto.toDto(post);
	}

	@Override
	public PostDto updateAll(PostDto dto) {
		
		
		//Entity 의 id 를 제외한 모든 필드를 수정한다.
		Post post = repo.save(Post.toEntity(dto));
		
		
		//수정된 Entity 를 dto로 변경해서 리턴하기
		return PostDto.toDto(post);
	}
	/*
	 * JPA 에서 동일한 Transaction 내에서 특정 Entity 를 find 한 다음
	 * 해당 Entity의 setter 메소드를 이용해서 특정 필드를 수정하면
	 * Transaction 이 종료될 때 Entity 가 변경되었는지를 확인해서
	 * 자동으로 DB 에 변경된 내용만 수정 반영한다.(더티 체크)
	 */
	@Transactional //update() 메소드를 단일 transaction 으로 묶어준다
	@Override
	public PostDto update(PostDto dto) {
		//post id 를 이용해서 수정할 Entity 를 얻어온다
		Post post = repo.findById(dto.getId()).orElseThrow();
		//title 이 null이 아닐 때만 title 수정
		if(dto.getTitle() != null ) {
			post.setTitle(dto.getTitle());
		}
		// 위의 코드를 한 줄로 표현하면 아래와 같다
		//Optional.ofNullable(dto.getTitle()).ifPresent(post::setTitle);
		
		//author 이 null이 아닐 때만 author 수정
		if(dto.getAuthor() != null) {
			post.setAuthor(dto.getAuthor());
		
		}
		//Optional.ofNullable(dto.getAuthor()).ifPresent(post::setAuthor);
		
		return PostDto.toDto(post);
	}

	@Override
	public PostDto find(long id) {
		//post 를 찾고 없으면 예외 발생
		Post post = repo.findById(id).orElseThrow();
		//찾은 Entity 를 dto 로 변경해거 리턴하기
		return PostDto.toDto(post);
	}
	
}
