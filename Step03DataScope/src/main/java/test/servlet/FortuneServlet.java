package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//오늘의 운세를 얻어오는 비즈니스 로직을 수행(db에서 읽어왔다 가정)
		String fortune="동쪽으로 가면 귀인을 만나요";
		//html 형식으로 응답하려니 머리가 지끈거린다...
		//webapp/ 어딘가에 있는 jsp 페이지에게 대신 응답해달라고 응답을 위임할 수잇다
		//대신 응답에 필요한 데이터는 request scope 에 담아서 전달해주어야 한다
		//req.setAttribute(key, value(아무거나 다 담을 수 있다));
		req.setAttribute("fortuneToday", fortune);
		
		//응답은 jsp 페이지에 위임한다(forward 이동)
		//.getRequestDispatcher("응답을 위임할 jsp 페이지의 위치")
		RequestDispatcher rd = req.getRequestDispatcher("/test/fortune.jsp");
		//jsp 페이지는 servlet 에서 보낸 req, resp 객체를 사용할 수 있다
		//(String)request.getAttribute("fortuneToday")
		//어떤 key 값으로 어떤 value 를 담았는지 확인
		rd.forward(req, resp);
	}
}