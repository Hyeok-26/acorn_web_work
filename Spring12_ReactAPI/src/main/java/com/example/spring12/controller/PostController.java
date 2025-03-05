package com.example.spring12.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@RequestMapping("/v1")//모든 요청에 대해 v1으로 들어가게 하겠다
@RestController 
public class PostController {
	
	//@ResponseBody 가 RestController 에서는 기본이다
	/*
	 * 	보통 API 서버에는 클라이언트가 json 문자열을 전송한다.
	 *  해당 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 라는 어노테이션이 필요하다.
	 */
	@Autowired private PostRepository repo;
	
	@PostMapping("/posts")
	public PostDto insert(@RequestBody  PostDto dto){
		//dto 를 Entity 로 변경해서 save 메소드에 전달한다.
		Post post = repo.save(Post.toEntity(dto));// 방금 저장된 정보가 들어있는 Entity 가 리턴된다
		
		//Entity 를 dto 로 변경해서 리턴하기
		return PostDto.toDto(post);
	}
	//글 목록 요청 처리
	@GetMapping("/posts")
	public List<PostDto> list(){
		//Entity List 를 dto List 로 변경해서 리턴해주기
		List<PostDto> list = repo.findAll().stream().map(PostDto :: toDto).toList();
		return list;
	}
	//@PathVariable=경로를 통해 들어오는 id 의 값을 int id 에 넣겠다
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) {
		//삭제할 post 를 읽어온다
		Post post = repo.findById(id).get();
		
		//id 를 이용해서 삭제한다.
		repo.deleteById(id);
		
		//이미 삭제한 데이터를 응답해준다
		return PostDto.toDto(post);
	}
	
	@PutMapping("/posts/{id}")
	public PostDto updateAll(@PathVariable("id") long id,@RequestBody PostDto dto) {
		dto.setId(id);
		// Entity 의 id 가 null 이 아니기 때문에 insert 가 아닌 update 가 수행된다
		repo.save(Post.toEntity(dto));
		
		return dto;
	}
}	
