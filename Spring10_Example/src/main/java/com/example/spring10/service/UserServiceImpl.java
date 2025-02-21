package com.example.spring10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.UserDto;
import com.example.spring10.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	//SercurityConfig 클래스에 @Bean 설정으로 bean 이된 passwordEncoder 객체 주입 받기
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public UserDto findByNum(long num) {
		
		return dao.getData(num);
	}

	@Override
	public UserDto findByUserName(String userName) {
		
		return dao.getData(userName);
	}

	@Override
	public void save(UserDto dto) {
		//저장할 때 비밀번호를 암호화 한 후에 저장되도록 한다
		String encodedPwd = encoder.encode(dto.getPassword());
		//인코딩된 결과를 다시 dto 객체에 넣어준다
		dto.setPassword(encodedPwd);
		//DB 에 저장한다
		int rowCount = dao.insert(dto);
		if(rowCount == 0) {
			throw new RuntimeException("회원정보 저장 실패!!!!!");
		}
		
	}

}
