package test.survlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pang")
public class TestSurvlet extends HttpServlet{
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//오늘의 운세를 어떤 로직에 의해서 얻어왔다고 가정하자
			String Today = "쓰러져!!";
			
			//응답 인코딩 설정
			resp.setCharacterEncoding("utf-8");
			//응답 컨텐트 설정
			resp.setContentType("text/html; charset=utf-8");
			//요청을 한 클라이언트에게 문자열을 출력할 수 있는 객체
			PrintWriter pw = resp.getWriter();
			pw.println("<!doctype html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<meta charset = 'utf-8'>");
			pw.println("<title></title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>오늘의 운세: <strong>"+Today+"</strong></p>");
			pw.println("</body>");
			pw.println("</html>");
			pw.close();
		}
		
}
