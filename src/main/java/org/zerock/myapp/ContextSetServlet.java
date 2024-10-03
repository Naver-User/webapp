package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리
			req.setCharacterEncoding("utf8");	// UTF-8, UTF8, utf-8, utf8
			
			// 1-1. 공유속성(= 공유영역에 공유할 데이터) 생성
			String name = "Yoseph";
			int age = 23;
			
			// 1-2. 공유영역(Application Scope)에 공유속성 바인딩(즉, 올려놓아요!)
			ServletContext sc = this.getServletContext();
			sc.setAttribute("NAME", name);			// 이름 공유속성 바인딩(공유하겠다!)
			sc.setAttribute("AGE", age);			// 나이 공유속성 바인딩
			
			// 2. 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			
			
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // servlet

} // end class


