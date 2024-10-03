package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2

public class EmpDAO {
	private DataSource dataSource;
	
	
	public EmpDAO() throws NamingException {
		log.trace("Default Constructor invoked.");
		
		InitialContext ctx = new InitialContext();
		this.dataSource = (javax.sql.DataSource) 
			ctx.lookup("java:comp/env/jdbc/Oracle21c");
	} // Default constructor
	
	
	// (1) 전체사원목록 조회 및 반환
	public List<EmpVO> selectAllEmployees(EmpDTO dto) throws Exception {
		log.trace("selectAllEmployees({}) invoked.", dto);
		
		Objects.requireNonNull(this.dataSource);
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t+ conn: {}", conn);
		
		String sql = "SELECT * FROM emp";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup ResultSet rs = pstmt.executeQuery();		// DQL
		
		List<EmpVO> list = new ArrayList<>();
		
		while(rs.next()) {	// 각 행마다, 모든 컬럼의 값을 추출
			Integer empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			Date hireDate = rs.getDate("hireDate");
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
			
			list.add(vo);
		} // while
				
		return list;
	} // selectAllEmployees 
	
	// (2) 특정사원삭제
	public boolean deleteEmployee(EmpDTO dto) throws Exception {
		log.trace("deleteEmployee({}) invoked.");
		
		Objects.requireNonNull(dto.getEmpno());

		@Cleanup
		Connection conn = this.dataSource.getConnection();
		
		String sql = "DELETE FROM emp WHERE empno = ?";		// DML
		
		@Cleanup 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getEmpno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // deleteEmployee 
	
	// (3) 신규사원등록
	public boolean insertEmployee(EmpDTO dto) throws Exception {
		log.trace("insertEmployee({}) invoked.", dto);
		
		Objects.requireNonNull(dto.getEmpno());

		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = """
				INSERT INTO emp
				VALUES (?, ?, ?, ?, ?, ?, ?, ?)
				""";
								
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getEmpno());
		pstmt.setString(2, dto.getEname());
		pstmt.setString(3, dto.getJob());
		pstmt.setInt(4, dto.getMgr());
		pstmt.setDate(5, new java.sql.Date(dto.getHireDate().getTime()));
		pstmt.setDouble(6, dto.getSal());
		pstmt.setDouble(7, dto.getComm());
		pstmt.setInt(8, dto.getDeptno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // insertEmployee 
	
	// (3) 신규사원등록
	public boolean updateEmployee(EmpDTO dto) throws Exception {
		log.trace("updateEmployee({}) invoked.", dto);
		
		Objects.requireNonNull(dto.getEmpno());

		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = """
				UPDATE emp
				SET
					ename = ?,
					job = ?,
					mgr = ?,
					hireDate = ?,
					sal = ?,
					comm = ?,
					deptno = ?
				WHERE 
					empno = ?
				""";
								
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getEname());
		pstmt.setString(2, dto.getJob());
		pstmt.setInt(3, dto.getMgr());
		pstmt.setDate(4, new java.sql.Date(dto.getHireDate().getTime()));		// V
		pstmt.setDouble(5, dto.getSal());
		pstmt.setDouble(6, dto.getComm());
		pstmt.setInt(7, dto.getDeptno());
		pstmt.setInt(8, dto.getEmpno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // updateEmployee
	
	
	// (5) 특정사원 조회
	public EmpVO selectEmployee(EmpDTO dto) throws Exception {
		log.trace("selectEmployee({}) invoked.", dto);
		
		Objects.requireNonNull(this.dataSource);
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t+ conn: {}", conn);
		
		String sql = "SELECT * FROM emp WHERE empno = ?";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getEmpno());
		
		@Cleanup ResultSet rs = pstmt.executeQuery();		// DQL
		
		EmpVO vo = null;	// 한명의 사원정보를 저장할 VO 지역변수 선언
		
		if(rs.next()) {	// 각 행마다, 모든 컬럼의 값을 추출
			Integer empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			Date hireDate = rs.getDate("hireDate");
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			// VO 객체 생성
			vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);			
		} // if
				
		return vo;	// VO 객체 반환
	} // selectEmployee 
	

} // end class
