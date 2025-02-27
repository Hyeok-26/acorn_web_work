package com.example.spring10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.PostDto;
import com.example.spring10.exception.DeniedException;
import com.example.spring10.repository.CommentDao;
import com.example.spring10.repository.PostDao;

@Aspect	//aspect 역할을 하기 위한 어노테이션
@Component //bean 으로 만들기 위한 어노테이션
public class AuthAspect {
	
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;
	
	//매개변수를 읽어야 하는 시점에 실행해야 하기 때문에 사용
	@Around("execution(* com.example.spring10.service.*.delete*(..)) || execution(* com.example.spring10.service.*.update*(..))")
	public Object checkWriter(ProceedingJoinPoint joinpoint) throws Throwable {
		//aop 가 적용된 메소드 얻어내기
		String methodName = joinpoint.getSignature().getName();
		System.out.println(methodName+"메소드에 aop 적용됨");
		
		//수정, 삭제 작업을 할 글번호
		long num=0;
		
		//매개변수에 전달된 데이터를 Object[] 로 얻어내기
		Object[] args = joinpoint.getArgs();
		//반복문 돌면서 원하는 type 을 찾는다
		for(Object tmp :  args) {
			if(tmp instanceof Long) {
				num=(long)tmp;
			}else if(tmp instanceof PostDto) {
				num=((PostDto)tmp).getNum();
			}else if(tmp instanceof CommentDto) {
				num=((CommentDto)tmp).getNum();
			}
		}
		
		String writer=null;
		if(methodName.contains("Post")) {
			writer=postDao.getData(num).getWriter();
		}else if(methodName.contains("Comment")) {
			writer=commentDao.getData(num).getWriter();
		}
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new DeniedException("요청이 거부되었습니다");
		}
		
		//aop 가 적용된 메소드를 실행한다.
		Object obj = joinpoint.proceed();//해당 메소드를 실행시키기 위함
		
		return obj;
	}
	
}
