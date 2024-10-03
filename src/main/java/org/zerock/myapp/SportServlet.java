package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {			
			// 전송파라미터의 값을 getter 메소드로 얻어내기 전에
			// 가능하면 아래 코드로, 문자집합을 미리 설정해 놓으세요!
			req.setCharacterEncoding("utf8");	// HTTP request 메시지에 대해서
			
			// HTTP request 메세지로 들어온 "모든" 전송파라미터의 이름을 획득하고
			// 이 획득한 각각의 전송파라미터명으로, 각 전송파라미터의 갑을 얻고자 할 때
			// 사용하기도 합니다.(즉, 잘 사용하지는 않습니다..사실 디버깅용으로 사용)
			Enumeration<String> emu = req.getParameterNames();
			while(emu.hasMoreElements()) {
				String name = emu.nextElement();
				String[] values = req.getParameterValues(name);
				
				log.info("name({}), values({})", name, Arrays.toString(values));
			} // while
			
			//----------
			
			String[] sports = req.getParameterValues("sports");		// 여러값을 전송하는 파라미터
			
			String gender = req.getParameter("gender");				// 1개의 값만 전송하는 파라미터
			log.info("\t+ gender: {}", gender);
			
			// ---------------------
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf-8");	// then, 이번엔 소문자로지정
			
			PrintWriter out = res.getWriter();
			
			// 응답메시지의 바디영역에 출력하는 코드 작성
			for(String sport : sports) {
				out.println("sport: " + sport);
			} // enhanced
			
			out.println("gender:" + gender);
			
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

