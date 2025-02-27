package com.example.spring10.service;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	
	@Value("${file.location}")
	private String fileLocation;
	@Autowired private FileDao fileDao;
	
	@Override
	public long uploadFile(FileDto dto, MultipartFile myFile) {
		String uploader = SecurityContextHolder.getContext().getAuthentication().getName();
        dto.setUploader(uploader);
        
        // 파일이 비어있다면 예외 발생
        if (myFile.isEmpty()) {
            throw new RuntimeException("파일이 업로드되지 않았습니다.");
        }

        //원본 파일명
        String orgFileName = myFile.getOriginalFilename();
  		//파일의 크기
  		long fileSize = myFile.getSize();

  		//저장할 파일의 이름을 Universal Unique 한 문자열로 얻어내기
  		String saveFileName = UUID.randomUUID().toString();
  		//저장할 파일의 전체 경로 구성하기
		String filePath = fileLocation +File.separator+saveFileName;
		try {
			//업로드할 파일을 저장할 파일 객체 생성
			File saveFile = new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e){
			e.printStackTrace();
		}

        // DB 저장을 위한 DTO 설정
        dto.setOrgFileName(orgFileName);
        dto.setSaveFileName(saveFileName);
        dto.setFileSize(fileSize);

        // 파일 정보 DB 저장 후 파일 번호 반환
        long num = fileDao.getSequence(); // 시퀀스 가져오기
        dto.setNum(num);
        fileDao.upload(dto);
        
        return num;
	}

	@Override
	public List<FileDto> getList() {
        return fileDao.getList();
    }

	@Override
	public void deleteFile(long num) {
		fileDao.delete(num);
	}

	@Override
	public ResponseEntity<InputStreamResource> download(FileDto dto) {
		
		//원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
		try {
			//다운로드 시켜줄 원본 파일명(인코딩해서 브라우저에게 알려준다)
			String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
			//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보(정석 : value 는 이런 값)
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.
			headers.setContentLength(dto.getFileSize());
			
			//읽어들일 파일의 경로 구성
			String filePath=fileLocation + File.separator + dto.getSaveFileName();
			System.out.println(dto.getOrgFileName());
			System.out.println(dto.getSaveFileName());
			System.out.println(dto.getFileSize());
			//파일에서 읽어들일 스트림 객체
			InputStream is = new FileInputStream(filePath);
			//InputStreamResource 객체의 참조값 얻어내기
			InputStreamResource isr = new InputStreamResource(is);
			//ResponseEntity 객체를 구성해서
			ResponseEntity<InputStreamResource> resEntity = ResponseEntity.ok()
					.headers(headers)
					.body(isr);
			//리턴해주면 파일이 다운로드 된다
			return resEntity;
		}catch(Exception e){
			//예외 정보 콘솔 출력
			e.printStackTrace();
			//예외 발생시키기 
			throw new RuntimeException("파일을 다운로드 하는 중에 에러 발생!");
		}
	}

}
