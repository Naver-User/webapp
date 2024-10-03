package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@WebServlet("/EmpSelect")
public class EmpSelectServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/Oracle21c")
	private DataSource dataSource;		// 데이터소스객체의 자동주입 by 서블릿 컨테이너

	
	// 전처리(Pre-processing)
	@PostConstruct
	void postConstruct() {	// 필드에 데이터소스 객체가 잘 "주입(Injection)"되었는지 확인
		log.trace("postConstruct() invoked.");
		
		Objects.requireNonNull(this.dataSource);
		log.info("\t+ this.dataSource: {}", this.dataSource);
	} // postConstruct
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리 ------------
			
			// 필드에 저장된 커넥션 풀에서 한개의 가용한 커넥션을 대여받음
			@Cleanup Connection conn = this.dataSource.getConnection();
			log.info("\t+ conn: {}, type: {}", conn, conn.getClass().getName());
			
			
			// 2. 응답처리 ------------
			
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
//			res.setContentType("application/octet-stream; charset=utf8");
			
			PrintWriter out = res.getWriter();
					
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			try (conn; pstmt; rs;) {
			
				while(rs.next()) {
					String empno 	= rs.getString("empno");
					String ename 	= rs.getString("ename");
					String sal 		= rs.getString("sal");
					String deptno 	= rs.getString("deptno");
					
					// 응답문서로 출력
					out.println( "<h2>%s\t%s\t%s\t%s</h2>".formatted(empno, ename, sal, deptno) );				
				} // while
			
			} finally {
				out.flush();
			} // try-with-resources
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class


