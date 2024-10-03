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

@WebServlet("/B")
public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // --------------------------------------
			 // (1) 요청처리로직 분담
			 // --------------------------------------
			 // 계좌이체 - (2) 계좌의 유효성 검증
			 log.info("\t+ (2) 계좌의 유효성 검증");
			 

			 // --------------------------------------
			 // (2) 응답메시지의 바디에 출력
			 // --------------------------------------
			 @Cleanup PrintWriter out = res.getWriter();
			 
			 out.println("/B");
			 
//			 out.flush();

			 // --------------------------------------
			 // (3) 요청포워딩 수행
			 // --------------------------------------
			 // 요청 위임시키자!(Request Forwarding)
			 RequestDispatcher dispatcher = req.getRequestDispatcher("/C");
			 dispatcher.forward(req, res);
			 
			 log.info("B");
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

