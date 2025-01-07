package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//이 클래스로 만든 객체가 tomcat 서버에서
//ping 요청이 오면 직접 응답을 하도록
@WebServlet("/ping")
public class PingServlet extends HttpServlet {//1.HttpServlet 클래스 상속
	
	//2.service() 메소드 오버라이딩
	@Override                  //요청 ,                 응답
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//클라이언트에게 문자열을 출력할 수 있는 객체를 얻어낸다
		PrintWriter pw = response.getWriter();
		//이 객체를 이용해서 출력하는 문자열은 요청을 한 클라이넌트에게 출력된다
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset = 'utf-8'>");
		pw.println("<title>나의 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>pong!</h1>");
		pw.println("</body>");
		pw.println("</html>");
		pw.println("pong!");
		pw.close();
	}
}