package com.example.spring10.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;

public interface FileService {
	public long uploadFile(FileDto dto, MultipartFile myFile);
	public List<FileDto> getList();
	public void deleteFile(long num);
	public ResponseEntity<InputStreamResource> download(FileDto dto);
}
