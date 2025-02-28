package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.PostDto;

public interface FileDao {
	public List<FileDto> getList(); //게시글 목록
	public int upload(FileDto dto); //파일 업로드
	public int delete(long num); //파일 삭제
	public int update(FileDto dto); //파일 수정
	public FileDto getData(long num); //특정 파일 다운받기 위한 
	public long getSequence();
}
