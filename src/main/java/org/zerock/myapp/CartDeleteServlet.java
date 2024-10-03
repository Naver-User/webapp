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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartDelete")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 현재 브라우저에 이름(=세션ID)으로만 열수잇는 금고상자(=Session Scope)에
	// 들어있는 물품목록을 삭제하는 역할 수행
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 세션획득
			 HttpSession session = req.getSession(false);	// OK, 역할상 이게 맞음
			 
			 // Step.2 장바구니 역할을 한 리스트를 공유속성(금고상자)에서 삭제
			 @SuppressWarnings("unchecked")
			List<String> basket = 
					 (List<String>) session.getAttribute(CartSaveServlet.BASKET);
			 
			 session.removeAttribute(CartSaveServlet.BASKET);
			 
			 // Step.4 리스트도 파괴 -> GC가 빨리 되도록...
			 basket.clear();
			 
			 // Step.5 응답처리
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 PrintWriter out = res.getWriter();
			 out.println("Delete Done.");			 
			 out.flush();
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service

} // end class

