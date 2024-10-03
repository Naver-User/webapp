package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			String userid = req.getParameter("userid");
			String passwd = req.getParameter("passwd");
			
			log.info("\t+ userid: {}", userid);
			log.info("\t+ passwd: {}", passwd);
			
			// ----------------------
			// 응답 메시지 처리
			// ----------------------
			res.setContentType("text/html; charset=UTF-8");			// 1
			
			PrintWriter out = res.getWriter();						// 2
			out.println("<h1>1. userid: " + userid + "</h1>");
			out.println("<h1>2. passwd: " + passwd + "</h1>");
			
			// out.println() 메소드로 출력한 데이터가 단 1바이트라도 남아있을가능성이 있기 때문에, 
			// 완전하게 비워서, 모든 데이터를 확실히 출력하도록 하는 메소드
			out.flush();											// 3
		} catch(Exception e) {
			e.printStackTrace();
			
			// 원래발생된 예외를 Wrapping 해서, 서블릿의 지정된 예외로 던지라!!!(대전제)
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class


