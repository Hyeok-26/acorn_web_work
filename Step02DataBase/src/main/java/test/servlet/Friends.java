package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/friends")
public class Friends extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> name = new ArrayList<String>();
		name.add("이강권");
		name.add("송창엽");
		name.add("이우진");
		
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
			pw.println("<h1>나의 칭구들</h1>");
			for(String tmp:name) {
				pw.println("<li>"+tmp+"</li>");
			}
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
