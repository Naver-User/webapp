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

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리
			req.setCharacterEncoding("utf8");	// UTF-8, UTF8, utf-8, utf8
			
			// 1-1. 공유속성 얻기
			ServletContext sc = this.getServletContext();		
			
			// 1-2. Type mismatch: cannot convert from Object to String
			String name = (String) sc.getAttribute("NAME");
			Integer age = (Integer) sc.getAttribute("AGE");
			
			// 1-3. NAME 공유속성의 값을 변경
			sc.setAttribute("NAME", "Trinity");	// 공유속성의 값 변경(= Not un-binding)
			
			// 2. 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			
			// HTTP response 메시지의 Body(=Payload)에 출력
			out.println("1. name: " + name);
			out.println("2. age: " + age);
			
			out.flush();	// 출력버퍼에 잔류할 수 있는 모든 바이트를 강제로 비우자!!
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
