package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(
	urlPatterns = { "/InitParamAnno" }, 
	initParams = { 
		@WebInitParam(name = "PARAM1", value = "VALUE1"), 
		@WebInitParam(name = "PARAM2", value = "VALUE2")
	})
public class InitParamAnnoServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	private String param1;
	private String param2;


	// 주의사항2: 각 서블릿의 등록된 초기화파라미터는, 아래의 init callback에서
	//            사용하는게 맞다!!!
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		log.info("\t+ PARAM1: {}", config.getInitParameter("PARAM1"));
		log.info("\t+ PARAM2: {}", config.getInitParameter("PARAM2"));
		
		// 다른 메소드에서도 초기화 파라미터가 필요하다면, 필드에 저장
		this.param1 = config.getInitParameter("PARAM1");
		this.param2 = config.getInitParameter("PARAM2");
	} // init
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// == 요청처리 =====
			req.setCharacterEncoding("UTF-8");
			
			// ** 주의사항: init callback 에서, 초기화 파라미터를 획득하면
			//				더이상 service callback 에서는, 상속받은 아래의 메소드로는
			//				초기화 파라미터를 얻을 수 없습니다.(무조건 오류발생)
//			log.info("\t+ PARAM1: {}", this.getInitParameter("PARAM1"));
//			log.info("\t+ PARAM2: {}", this.getInitParameter("PARAM2"));
			
			log.info("\t+ PARAM1: {}", this.param1);
			log.info("\t+ PARAM2: {}", this.param2);
			
			
			// == 응답처리 =====
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		} // try-catch
	} // service

} // end class
