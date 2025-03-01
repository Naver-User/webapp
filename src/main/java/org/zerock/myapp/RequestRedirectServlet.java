package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/RequestRedirect")
public class RequestRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 Request Scope 에 공유속성 2개 바인딩
			 req.setAttribute("KEY1", "VALUE1");
			 req.setAttribute("KEY2", "VALUE2");
			 
			 // Step.2 리다이랙션 응답을 브라우저에 전송
			 // 웹브라우저님!, 아래에 지정한 URI로 다시 재요청을 보내셔야 합니다!
			 // 라는 내용을 가지고 있는 응답 전송(
			 res.sendRedirect("/ResponseRedirect");
			 
			 // Step.3 Step.2 가 수행된 이후에도, 아래코드가 수행될까!?를 실험
			 log.info("Done.");
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

