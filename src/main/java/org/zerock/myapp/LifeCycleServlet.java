package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletConfig;
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

@WebServlet("/LifeCycle")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String driver = "oracle.jdbc.OracleDriver";
	private final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private final String user = "HR";
	private final String pass = "oracle";
	
	private Connection conn;

	
	// Servlet Container 가 이 서블릇 클래스로부터 생성자 호출 직후에
	// 호출(callback) 하는 메소드 => 전처리 작업은 여기서 하라!!
	@Override
	public void init(ServletConfig config) throws ServletException { // 새로운 JDBC Connection 생성	
		log.trace("init({}) invoked.", config);
		
		// Create a new JDBC connection.
		try {
			Class.forName(driver);
			
			this.conn = DriverManager.getConnection(url, user, pass);
			log.info("\t+ conn: {}", conn);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // init


	// Servlet Container 가, 이 서블릿 객체를 파괴하기 직전에 호출하는 메소드
	// => 후처리 작업은 여기서 하라!!
	@Override
	public void destroy() {							// init callback에서 생성한 JDBC Connection 닫기
		log.trace("destroy() invoked.");
		
		// 필드에 보관된 Connection 객체를 닫자!(왜? 아주비싼 자원객체이니까...)
		try {
			if(this.conn != null) {			
				this.conn.close();
				log.info("\t+ this.conn closed successfully.");
			} // if
		} catch(Exception ignored) {}
	} // destroy


	// 요청을 처리하고, 응답을 만들어 보내는 메소드
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			@Cleanup
			PrintWriter out = res.getWriter();
			
			if(this.conn != null) {
				final String sql = "SELECT * FROM employees ORDER BY employee_id";
				@Cleanup PreparedStatement pstmt = this.conn.prepareStatement(sql);
				@Cleanup ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int employeeId = rs.getInt("EMPLOYEE_ID");
					String firstName = rs.getString("FIRST_NAME");
					String lastName = rs.getString("LAST_NAME");
					String email = rs.getString("EMAIL");
					String phoneNumber = rs.getString("PHONE_NUMBER");
					Date hireDate = rs.getDate("HIRE_DATE");
					String jobId = rs.getString("JOB_ID");
					double salary = rs.getDouble("SALARY");
					double commissionPct = rs.getDouble("COMMISSION_PCT");
					int managerId = rs.getInt("MANAGER_ID");
					int departmentId = rs.getInt("DEPARTMENT_ID");	
					
					String employee = 
							"%s %s %s %s %s %s %s %s %s %s %s"
							.formatted(
								employeeId, firstName, lastName, email, phoneNumber,
								hireDate, jobId, salary, commissionPct, managerId, departmentId
							);
					
					log.info("\t+ employee: {}", employee);
					
					out.println(employee);
				} // while
			} else {
				throw new IOException("this.conn is NULL");
			} // if-else
			
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		} // try-catch
	} // service

} // end class

