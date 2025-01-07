package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/send?msg")
public class FormServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("msg");
		System.out.println("폼 전송된 내용: "+msg);
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
			pw.println("<p>안녕?</p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	
	}
}
