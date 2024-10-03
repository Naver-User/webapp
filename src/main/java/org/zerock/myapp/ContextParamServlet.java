package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Objects;

import javax.servlet.ServletConfig;
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

@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	private String contextParam1;
	private String contextParam2;


	// 1. 서블릿의 초기화 파라미터를 얻을 때에는 ServletConfig 객체가 필요
	//    이 ServletConfig 객체가, 다른 서블릿의 핵심객체들을 얻어낼 수 있는
	//	  "관문"역할을 한다!!!
	// 2. 공유된느 초기화 파리미터(컨텍스트파라미터)를 얻을 때에는,
	//	  ServletContext 객체가 필요하다!!!

	// 주의사항: init callback에서, 인자로 주어진 ServletConfig 객체를 사용하거나
	//           init callback에서, ServletConfig 를 통해 얻은, ServletContext 를 
	//			 사용하면, 더이상 service callback에서는, 상속된 메소드가 정상작동
	//           못하고 NullPointerException 이 발생한다!!!
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		try {
			ServletContext sc = config.getServletContext();
			
			String contextParam1 = sc.getInitParameter("CONTEXT_PARAM1");
			String contextParam2 = sc.getInitParameter("CONTEXT_PARAM2");
			
			// 인자로 전달한 값이 null 인지 체크해서,
			// 만일 null 이면, 자동으로 NullPointerException을 발생시키고
			// 만일 null 이 아니면, 아무일 없었듯이, 그냥 쑥~ 지나갑니다.
			Objects.requireNonNull(contextParam1);
			Objects.requireNonNull(contextParam2);
			
			this.contextParam1 = contextParam1;
			this.contextParam2 = contextParam2;
			
			log.info("contextParam1: {}", contextParam1);
			log.info("contextParam2: {}", contextParam2);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // init

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 요청처리
			req.setCharacterEncoding("UTF-8");
			
			// 모든 웹컴포넌트에서 가져다 사용할 수 있는, 공유되는 초기화 파라미터인
			// 컨텍스트 파라미터를 획득
		
			log.info("contextParam1: {}", this.contextParam1);
			log.info("contextParam2: {}", this.contextParam2);
			
			
			// 응답처리
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			
			
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class



