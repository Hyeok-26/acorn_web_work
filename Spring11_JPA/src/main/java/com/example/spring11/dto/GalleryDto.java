package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GalleryDto {
	private long id;
	private String writer;
	private String title;
	//dto의 날짜는 보통 String type 으로 선언
	private String createdAt;
	
	//Entity를 dto로 만들어주는 static 메소드
	public static GalleryDto toDto(Gallery gallery) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//2025.03.04 화 오후 3:10:25 형식의 문자열을 얻어낼 수 있는 객체이다.
		String result=sdf.format(gallery.getCreatedAt());
		
		return GalleryDto.builder()
				.id(gallery.getId())
				.writer(gallery.getWriter())
				.title(gallery.getTitle())
				.createdAt(result)
				.build();
	}
}
