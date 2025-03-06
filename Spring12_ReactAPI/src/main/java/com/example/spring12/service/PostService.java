package com.example.spring12.service;

import java.util.List;

import com.example.spring12.dto.PostDto;
import com.example.spring12.dto.PostPageResponse;

public interface PostService {
	public List<PostDto> findAll();
	public PostDto save(PostDto dto);
	public PostDto delete(long id);
	public PostDto updateAll(PostDto dto);
	public PostDto update(PostDto dto);
	public PostDto find(long id);
	//페이지에 해당되는 목록만 리턴
	public PostPageResponse findPage(int pageNum);
}
