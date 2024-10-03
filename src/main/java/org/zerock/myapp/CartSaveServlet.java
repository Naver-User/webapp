package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

@WebServlet("/CartSave")
public class CartSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String BASKET = "_BASKET_"; 

		
	// 한개 이상의 물품명을 저장가능하도록 수정
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 세션획득
			 HttpSession session = req.getSession();
			 
			 // Step2. 장바구니 역할 수행할 리스트만들어서, 세션금고상자에 바인딩
			 Object obj = session.getAttribute(BASKET);
			 log.info("\t+ obj: {}", obj);
			 
			 if(obj == null) { // 아직 세션금고상자안에 장바구니(리스트)가 없다면
				 List<String> basket = new ArrayList<>();	// 장바구니 만들어서 추가
				 session.setAttribute(BASKET, basket);
			 } // if
			 
			 // Step.3 전송파라미터로 온 물품명을 장바구니에 추가
			 List<String> savedBasket = 
				 (List<String>) session.getAttribute(BASKET);
			 
			 String product = req.getParameter("product");	// 전송파라미터 획득
			 savedBasket.add(product);						// 장바구니목록에 추가
			 
			 savedBasket.forEach(log::info);
			 
			 // Step.4 응답처리
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 @Cleanup PrintWriter out = res.getWriter();
			 out.println("장바구니에 물품명(%s)이 잘 추가되었습니다.".formatted(product));
			 out.flush();
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service

} // end class

