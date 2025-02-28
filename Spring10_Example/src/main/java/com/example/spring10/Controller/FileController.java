package com.example.spring10.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Value("${file.location}")
	private String fileLocation;
	@Autowired private FileService service;
	
	@GetMapping("/file/list")
	public String list(Model model) {
		//서비스를 이용해서 파일목록 얻어오기
		List<FileDto> list= service.getFiles();
		//응답에 필요한 데이터를 Model 객체에 담는다
		model.addAttribute("list", list);
		//template 페이지에 응답
		return "file/list";
	}
	
	@GetMapping("/file/new")
	public String newForm() {
			
		return "file/new";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto,RedirectAttributes ra) {
		//dto 에는 title 과 myFile 이 들어있다
		service.uploadFile(dto);
		
		ra.addFlashAttribute("saveMessage","파일 업로드 성공!");
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/delete")
	public String delete(int num) {
		service.deleteFile(num);
		return "file/delete";
	}
	
	//파일 다운로드
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(long num) {
		
		
		return service.download(num);
	}
}
