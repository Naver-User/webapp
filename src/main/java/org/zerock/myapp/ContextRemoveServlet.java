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

@WebServlet("/ContextRemove")
public class ContextRemoveServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리
			req.setCharacterEncoding("utf8");	// UTF-8, UTF8, utf-8, utf8
			
			// 1-1. 공유속성 언바인딩(삭제: 공유데이터 영역에서 공유속성 삭제처리)
			ServletContext sc = this.getServletContext();
			sc.removeAttribute("NAME");		// NAME 이름의 공유속성 삭제
			sc.removeAttribute("AGE");		// AGE 이름의 공유속성 삭제
			
			
			// 2. 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			
			// HTTP response 메시지의 Body(=Payload)에 출력
			out.println("1. NAME 공유속성 삭제 성공");
			out.println("2. AGE  공유속성 삭제 성공");
			
			out.flush();	// 출력버퍼에 잔류할 수 있는 모든 바이트를 강제로 비우자!!
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
