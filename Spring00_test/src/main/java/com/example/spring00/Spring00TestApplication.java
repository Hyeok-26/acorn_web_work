package com.example.spring00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
//사진첩 폴더경로 파일 로딩을 위한 어노테이션
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Spring00TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring00TestApplication.class, args);
	}

}
