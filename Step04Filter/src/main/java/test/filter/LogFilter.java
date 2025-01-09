package test.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/*
 * [필터 만드는 방법]
 * 
 * 1.Filter 인터페이스 구형
 * jakarta
 * 2.추상 메소드 재정의
 * 3.@WebFilter 이노테이션을 이용해서 요청 맴핑하기
 * 
 * 필터를 만들어서 어떻게 맵핑하는 지
 */

@WebFilter("/*") // "/*" 이 프로젝트(컨텍스트) 에 오는 모든 요청에 대해서 필터가 동작 되게 된다.
public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Log 필터 수정됨!");
		
		//부모 type  객체를 원래 type (자식type) 으로 casting
		HttpServletRequest request = (HttpServletRequest)req;
		String url = request.getRequestURI();
		System.out.println("요청 url:"+url);
		String clientIp = request.getRemoteAddr();
		System.out.println("client ip: "+ clientIp);
		
		System.out.println("시간: "+LocalDateTime.now());
		
		
		//요청의 흐름 계속 이어가기
		chain.doFilter(req, resp);
	}

}
