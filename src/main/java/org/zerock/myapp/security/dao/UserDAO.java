package org.zerock.myapp.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.security.domain.UserVO;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@NoArgsConstructor
public class UserDAO {
	// JNDI lookup 이용해서, 커넥션풀 저장
	private DataSource dataSource;
	

	public UserDAO() throws NamingException {	// JNDI lookup 으로 DataSource 레퍼런스 필드에 저장
		log.trace("Default Constructor invoked.");
		Context ctx = new InitialContext();
		
		Object foundObj = ctx.lookup("java:comp/env/jdbc/Oracle21c");
		log.info("\t+ foundObj: {}", foundObj);
		
		Objects.requireNonNull(foundObj);
		this.dataSource = (DataSource) foundObj;
	} // Default Constructor
	
	
	public UserVO selectByUserName(String username) throws Exception {
		log.trace("selectByUserName({}) invoked.", username);
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = "SELECT * FROM t_user WHERE username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			String password = rs.getString("password"); 
			String role = rs.getString("role");
			
			return new UserVO(username, password, role);
		} // if
		
		return null;	// No found.
	} // selectByUserName

} // end class
