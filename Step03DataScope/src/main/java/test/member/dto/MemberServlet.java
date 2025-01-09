package test.member.dto;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 한 명의 정보를 db에서 읽어왔다 가정
		MemberDto dto = new MemberDto(1, "김구라", "노량진");
		//회원 한명의 정보를 담고 있는 객체를 scope 에 "dto" 라는 키 값으로 담기
		request.setAttribute("dto", dto);
		//	"/test/member.jsp" 페이지로 응답을 위임시켜 회원 한명의 정보를 응답하도록 프로그래밍 하기
		request.getRequestDispatcher("/test/member.jsp").forward(request, response);
		
	}
}
