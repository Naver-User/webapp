package org.zerock.myapp.security.service;

import java.util.Objects;

import org.zerock.myapp.security.dao.UserDAO;
import org.zerock.myapp.security.domain.LoginDTO;
import org.zerock.myapp.security.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SignInService {
	
	
	// 로그인처리(즉, "인증처리") 수행)
	public boolean loginProcess(LoginDTO dto) throws Exception {
		log.trace("loginProcess({}) invoked.", dto);
		
		UserDAO dao = new UserDAO();
		
		UserVO vo = dao.selectByUserName(dto.getUsername());
		Objects.requireNonNull(vo);
		
		if(dto.getPassword().equals(vo.getPassword())) {	// 로그인정보가 일치
			return true;		// 인증성공
		} else {
			return false;		// 인증실패
		} // if-else
	} // signIn
	
	
	// 지정된 사용자에 해당하는 정보를 VO로 반환
	public UserVO findByUserName(LoginDTO dto) throws Exception {
		log.trace("findByUserName({}) invoked.", dto);
		
		UserDAO dao = new UserDAO();
		UserVO vo = dao.selectByUserName(dto.getUsername());
		
		// 지정된 username으로 테이블에서 찾지 못했으면, null반환
		return vo;
	} // findByUserName

} // end class
