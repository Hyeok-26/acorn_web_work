  package com.example.spring08;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring08.util.Messenger;
import com.example.spring08.util.WritingUtil;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring08JavaApplication {
	
	@Autowired
	private WritingUtil util;
	
	@Autowired
	private Messenger messenger;
	//@SpringBootApplication클래스로 객체가 생성된 직후(spring 이 준비가 완료된 직후)
	@PostConstruct
	public void testAop() {
		messenger.sendGreeting("안녕 하세요!");
		messenger.sendGreeting("안녕 똥개야!");
		
		String result = messenger.getMessage();
		System.out.println("result : "+result);
		
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
		
		String pwd = "1234";
		
		//비밀번호를 암호화해주는 객체
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//암호화된 비밀번호 얻어내기
		String encodedPwd = encoder.encode(pwd);
		//결과를 콘솔창에 출력
		System.out.println(encodedPwd);
		
		//날것의 비밀번호와 암호화된 비밀번호가 같은디 여부 확인하기
		boolean isVaild = BCrypt.checkpw("4567",encodedPwd);
		System.out.println("일치 여부 : "+isVaild);
		
		// of() 메소드로 만든 List 는 읽기 전용(Read Only) 이다
		List<String> names = List.of("김구라","해골","원숭이");
		//names.add("주뎅이"); 동작하지 않는다
		
		//List 의 stream() 메소드를 호출하면 Stream type 이 리턴된다.
		Stream<String> stream = names.stream();
		//람다식 함수 적용
		Function<String, String> f = (item) -> {
			return item+"님";
		};
		//위의 Function type 을 줄여서 쓰면 아래와 같다
		Function<String, String> f2 = (item) -> item+"놈";
		List<String> names2 = stream.map(f).toList();
		System.out.println(names2);
		//stream 에서 한 번 사용하면 끝.일회성 
		
		List<String> names3 = names.stream().map(f2).toList();
		System.out.println(names3);
		
		List<String> names4 = names.stream().map(item->item+"님").toList();
		System.out.println(names4);
		
		List<Integer> nums = List.of(-10, 20, 30, -5, 25, -30);
		//nums 에서 양의 정수만 남겨진 새로우 List 얻어내기
		List<Integer> nums2 = nums.stream().filter(item -> item>0).toList();
		System.out.println(nums2);
		//nums 에서 양의 정수만 남기고 2바를 곱한 새로운 List 얻어내기
		List<Integer>newNums = nums.stream().filter(item -> item>0).map(item->item*2).toList();
		System.out.println(newNums);
		
		//nums에서 양의 정수만 남기고 2배를 곱한 새로운 List 얻어내서 순서대로 해당숫자를 곱한 콘솔창에 모두 출력하기
		nums.stream().filter(item->item>0).map(item->item*2).forEach(item->{
			System.out.println(item);
		});
		
		//문자열(string) 이 들어있는 리스트
		List<String> strNum = List.of("10","20","30","40","50");
		
		//위의 List 를 활용해서 List<Integer> 를 얻어내 보세요
		List<Integer> intNums =  strNum.stream().map(item->Integer.parseInt(item)).toList();
		System.out.println(intNums);
		
		//함수 or 객체안 메소드를 참조하는 메소드가 존재한다?!?!
		//Integer 클래스가 가지고 있는 parseInt 메소드를 참조해서 map() 함수에 전달해서 동일한 작업도 가능하다
		List<Integer> intNums2 = strNum.stream().map(Integer::parseInt).toList();
		System.out.println(intNums2);
	}

}
