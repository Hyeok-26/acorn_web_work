package com.example.spring10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.UserDto;
import com.example.spring10.exception.PasswordException;
import com.example.spring10.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	//SercurityConfig 클래스에 @Bean 설정으로 bean 이된 passwordEncoder 객체 주입 받기
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public UserDto getByNum(long num) {
		
		return dao.getData(num);
	}

	@Override
	public UserDto getByUserName(String userName) {
		
		return dao.getData(userName);
	}

	@Override
	public void createUser(UserDto dto) {
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

	@Override
	public void updateUserInfo(UserDto dto) {
		
	}

	@Override
	public void changePassword(UserDto dto) {
		//1.로그인 된 username 얻어내기
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//2.기존의 비밀번로를 DB에서 읽어와서(암호화된 비밀번호)
		String encodedPwd = dao.getData(userName).getPassword();
		//3. 입력한(암호화 되지 않은 구비밀번호) 와 일치하는지 비교해서
		//checkpwd(암호화되지 않은 비밀번호, 암호화된 비밀번호)
		boolean isValid = BCrypt.checkpw(dto.getPassword(), encodedPwd);
		//4. 일치하지 않으면 Exception 일으킨다
		if(!isValid) {
			throw new PasswordException("기존 비밀번호가 일치하지 않습니다!");
		}
		//5.일치하면 새비밃ㄴ호를 암호화해서 dto에 담은 다음
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		//6. userName 도 dto 에 담고 
		dto.setUserName(userName);
		//7.DB 에 비밃번호 수정반영을 한다
		int rowCount = dao.updatePassword(dto);
		if(rowCount == 0) {
			
		}
	}

}
