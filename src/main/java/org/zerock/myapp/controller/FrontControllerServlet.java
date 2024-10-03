package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// 역할: FC 패턴의 역할
// (1) 집중된 요청마다, "요청식별"
// (2) 모든 전송파라미터 수집
// (3) (1)와 (2) 이용해서, 실제 각 요청을 누가(서비스계층의 특정 서비스)
//     처리할지 확인해서, 실제 요청처리를 위임(Delegation)시키는 역할

//@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step.1 요청명(Request URI) 획득
			String command = req.getRequestURI();
			log.info("\t+ command: {}", command);
			
			
			// Step.2 모든 전송파라미터 수집
			// 모든 전송파라미터를 획득해서 자체 자료구조에 보관합니다.
			Map<String, String[]> params = req.getParameterMap();
			params.forEach((key, value) -> {
				log.trace("forEach({}, {}) invoked.", key, value);
			});	// .forEach
						
			// Step.3 (1) 요청을 식별하고, 
			//		  (2) 식별된 요청을 처리할 서비스를 식별하고
			// 		  (3) 식별된 서비스에게 "완전하게" 처리를 위임(=Delegation)
			// 하는 역할이 바로 FC 패턴이다!!!!
			switch(command) {
				case "/insert" 	-> {}
				case "/delete" 	-> {}
				case "/update" 	-> {}
				case "/select" 	-> {}
				case "/list" 	-> {}
				default 		-> {}
			} // switch expressin
						
		} catch(Exception original) {
			throw new ServletException(original);
		} // try-catch
	} // service

} // end class
