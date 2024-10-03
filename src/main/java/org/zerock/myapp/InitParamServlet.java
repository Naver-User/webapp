package org.zerock.myapp;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

//@WebServlet("/InitParam")
public class InitParamServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);

		// web.xml 파일에 등록된 초기화파라미터 값을 얻어내기 위한
		// 파일작업도 요청이 올 때마다 수행하게 되는 위치.
		String param1 = config.getInitParameter("PARAM1");
		String param2 = config.getInitParameter("PARAM2");
		
		log.info("\t+ param1: {}", param1);
		log.info("\t+ param2: {}", param2);
	} // service
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			//---------------
			// 응답처리 
			//---------------
//			res.setCharacterEncoding("utf8");			 
//			res.setContentType("text/html; charset=UTF-8");
//			
//			PrintWriter out = res.getWriter();
//			 
//			out.print("<html><body>");
//			out.print("디렉터리경로: " + dirPath +"<br>");
//			out.print("아이디 값: " + userid +"<br>");
//			out.print("</body></html>");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		} // try-catch
	}

} // end class

