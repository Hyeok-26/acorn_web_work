package com.example.spring10.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;

public interface FileService {
	public long uploadFile(FileDto dto);
	public void updateFile(FileDto dto);
	public void deleteFile(long num);
	public List<FileDto> getFiles();
	public ResponseEntity<InputStreamResource> download(long num);
}
