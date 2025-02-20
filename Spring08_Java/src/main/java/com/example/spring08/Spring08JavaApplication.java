package com.example.spring08;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring08JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
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
		Function<String, String> f2 = (item) -> item+"님";
		List<String> names2 = stream.map(f).toList();
		System.out.println(names2);
	}

}
