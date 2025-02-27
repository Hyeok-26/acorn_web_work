package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.PostDto;

public interface FileDao {
	public List<FileDto> getList();
	public int upload(FileDto dto);
	public int delete(long num);
	public FileDto getData(long num);
	public long getSequence();
}
