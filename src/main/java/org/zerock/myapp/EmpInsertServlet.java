package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet("/EmpInsert")
public class EmpInsertServlet extends HttpServlet {
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
			
			// Step.1 전송파라미터 획득
			String empId = req.getParameter("emp_id");
			String ename = req.getParameter("ename");
			String salary = req.getParameter("salary");
			String depart = req.getParameter("depart");
						
			// Step2. 필드에 저장된 커넥션 풀에서 한개의 가용한 커넥션을 대여받음
			@Cleanup Connection conn = this.dataSource.getConnection();
			log.info("\t+ conn: {}, type: {}", conn, conn.getClass().getName());

			// Step3. 입력데이터를 이용해서, SQL 문장처리(DML, INSERT) 수행
			String sql = "INSERT INTO emp (EMPNO, ENAME, SAL, DEPTNO) VALUES (?, ?, ?, ?)";
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(empId));
			pstmt.setString(2, ename);
			pstmt.setDouble(3, Double.parseDouble(salary));
			pstmt.setInt(4, Integer.parseInt(depart));
			
			int affectedRows = pstmt.executeUpdate();
			log.info("\t+ affectedRows: {}", affectedRows);
			
			// Step.4 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();			
			out.println( "<h1>affectedRows: %d</h1>".formatted(affectedRows) );
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class


