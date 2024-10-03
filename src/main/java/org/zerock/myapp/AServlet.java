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

@WebServlet("/A")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 // --------------------------------------
			 // (1) 요청처리로직 분담
			 // --------------------------------------
			 // 계좌이체 - (1) 본인확인 수행
			 log.info("\t+ (1) 본인확인 수행");
			 

			 // --------------------------------------
			 // (2) 응답메시지의 바디에 출력
			 // --------------------------------------
			 // 요청포워딩 발생전에, 응답메시지 바디에 출력한 데이터의
			 // 크기가 기본 8KB 이상 이미 출력햇다면, 포워드 전에
			 // 이전 데이터를 삭제불가능! -> 
			 @Cleanup PrintWriter out = res.getWriter();
			 
			 for(int i=1; i<1000000;i++) {
				 out.println("/A");
			 } // for
			 
//			 out.flush();
			 
			 
			 // --------------------------------------
			 // (3) 요청포워딩 수행
			 // --------------------------------------
			 // 요청 위임시키자!(Request Forwarding)
			 RequestDispatcher dispatcher = req.getRequestDispatcher("/B");
			 dispatcher.forward(req, res);
			 
			 log.info("A");
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

