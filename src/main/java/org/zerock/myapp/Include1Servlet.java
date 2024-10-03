package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/Include1")
public class Include1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // Step.1 현재 서블릿의 응답 생성
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			
			 @Cleanup PrintWriter out = res.getWriter();
			 out.println("HEADER<br>");
			 
			 
			 // Step.2 두번째 서블릿을 수행시켜, 그 응답을 포함시키기 위해서
			 //			Include 기법 수행			 
			 RequestDispatcher dispatcher2 =
				 // targetURI에는, 응답으로 포함시킬 다른 컴포넌트의 매핑 URI 지정
				 req.getRequestDispatcher("/Include2");
			 			 
			 dispatcher2.include(req, res);
			 
			 
			 // Step.3 세번째 서블릿을 수행시켜, 그 응답을 포함시키기 위해서
			 //			Include 기법 수행			 
			 RequestDispatcher dispatcher3 =
				 // targetURI에는, 응답으로 포함시킬 다른 컴포넌트의 매핑 URI 지정
				 req.getRequestDispatcher("/Include3");
			 			 
			 dispatcher3.include(req, res);

			 
			 out.flush();	// 생성한 응답메시지 커밋시킴
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

