package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartBasket")
public class CartBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 현재 브라우저에 이름(=세션ID)으로만 열수잇는 금고상자(=Session Scope)에
	// 들어있는 물품목록을 출력하는 역할 수행
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 세션획득
			 HttpSession session = req.getSession(false);	// OK, 역할상 이게 맞음
			 
			 @SuppressWarnings("unchecked")
			 List<String> basket = 
				 (List<String>) session.getAttribute(CartSaveServlet.BASKET);
			 
			 // Step.2 응답처리(응답에 물품명을 포함해서 전송)
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 @Cleanup PrintWriter out = res.getWriter();
			 
//			 basket.forEach(product -> {	// 1st. method
//				 out.println(product);
//			 }); // .forEach
			 
			 // 모든 Iterable 한 객체는 향상된 for문과 사용가능핟!!!
			 // 배열, List/Set/Map 컬렉션 등...
//			 for(String product  : basket) {	//	2nd. method
//				 out.println(product);
//			 } // enhanced for
			 
			 basket.forEach(out::println);	// 3st. method
			 
			 out.flush();
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service

} // end class

