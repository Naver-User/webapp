package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

// 첫번째 RequestServlet 으로부터, 요청처리를 위임받는 서블릿 컴포넌트
@WebServlet("/Response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step1. 이전 서블릿이 Request Scope 에 올려놓은 공유속성을 얻어내자!!
			 String key1 = (String) req.getAttribute("KEY1");
			 String key2 = (String) req.getAttribute("KEY2");
			
			 // Step2. 응답처리(Step1에서 얻어낸 공유속성을 응답으로 보내자)
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 @Cleanup PrintWriter out = res.getWriter();
			
			 // 공유속성의 값 2개를 가지고, 응답문서 만들자!!
			 out.println("KEY1: %s".formatted(key1));
			 out.println("KEY2: %s".formatted(key2));
			 
			 out.flush();
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

