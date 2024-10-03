package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet({ "/List", "/Create", "/Read", "/Update", "/Delete" })
public class EmpSelectDAOServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리

			// -----
			// 모든 전송파라미터를 획득해서, DTO에 저장
			EmpDTO dto = new EmpDTO();
			
			Enumeration<String> emu = req.getParameterNames();
			while(emu.hasMoreElements()) {
				String paramName = emu.nextElement();				// 전송파라미터명
				String paramValue = req.getParameter(paramName);	// 전송파라미터값
				
				log.info("(key, value) = ({}, {})", paramName, paramValue);
				
				switch(paramName) {
					case "empno":		dto.setEmpno(Integer.valueOf(paramValue)); break;
					case "ename":		dto.setEname(paramValue); break;
					case "job":			dto.setJob(paramValue); break;
					case "mgr":			dto.setMgr(Integer.valueOf(paramValue)); break;
					case "hireDate":	// paramValue = "2024-05-24T05:09"
						DateFormat formatter = 
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
						dto.setHireDate(formatter.parse(paramValue));
						break;
					case "sal":			dto.setSal(Double.valueOf(paramValue)); break;
					case "comm":		dto.setComm(Double.valueOf(paramValue)); break;
					case "deptno":		dto.setDeptno(Integer.valueOf(paramValue)); break;
					
					// 약속되지 않은 이름으로 전송된 파라미터가 있다면..
					default:
				} // switch
				
			} // while
			
			log.info("\t+ EmpDTO: {}", dto);
			
			
			// -----
			Object result = null;
			
			EmpDAO dao = new EmpDAO();
			
			String requestURI = req.getRequestURI();
			
			// 아래의 다중IF문 : 각 요청식별 -> DAO 메소드 호출 with DTO -> 요청처리
			if("/List".equals(requestURI)) {	// 1. 전체사원목록조회 요청
				result = dao.selectAllEmployees(dto);
			} else if("/Create".equals(requestURI)) {	// 2. 신규사원등록 요청(CREATE)
				result = dao.insertEmployee(dto);
			} else if("/Read".equals(requestURI)) {		// 3. 특정사원조회 요청(READ)
				result = dao.selectEmployee(dto);
			} else if("/Update".equals(requestURI)) {	// 4. 특정사원수정 요청(UPDATE)
				result = dao.updateEmployee(dto);
			} else if("/Delete".equals(requestURI)) {	// 5. 특정사원삭제 요청(DELETE)
				result = dao.deleteEmployee(dto);
			} // if-else
					
			
			// 2. 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			// 응답메시지의 Body에 출력하는 메소드 제공
			PrintWriter out = res.getWriter();
			// -----------
			
			out.println(result);
			
			// 1st. method
//			for(EmpVO vo : list) {	
//				out.println(vo + "<br>");
//			} // enhanced for
			
			// 2nd. method : forEach 메소드 & 람다식 이용
//			list.forEach( vo -> out.println(vo + "<br>") );
			
			// 3rd. method : 메소드 참조 이용
//			list.forEach(out::println);
			
			// 출력버퍼를 강제로 비워서, 혹시나 남아있는 출력데이터를 다 털어냄
			out.flush();	
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class

