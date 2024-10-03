package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ResponseRedirect")
public class ResponseRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 첫번째 서블릿이 바인딩한 공유속성을 Request Scope에서 획득
			 String key1 = (String) req.getAttribute("KEY1");
			 String key2 = (String) req.getAttribute("KEY2");
			 
			 // Step.2 Step.1 에서 획득한 공유속성으로 응답생성/전송
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 PrintWriter out = res.getWriter();
			 
			 out.println("1. key1: %s".formatted(key1));
			 out.println("2. key2: %s".formatted(key2));
			 
			 out.flush();	// 위에서 만든 응답메시지를 클라이언트로 전송해라!!
			 
			 // Step.3 Step.2 가 수행된 이후에도, 아래코드가 수행될까!?를 실험
			 log.info("Done.");
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

