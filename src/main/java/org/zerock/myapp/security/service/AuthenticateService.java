package org.zerock.myapp.security.service;

import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class AuthenticateService {
	
	
	// 핵심로직: 인증확인 로직 수행
	public boolean isAuthenticationPermitted(HttpSession session) {
		log.trace("isAuthenticationPermitted({}) invoked.", session);
		
		// 인증이 되었는가!?: true, 인증이 아직 안되었는가?: false
		 Object obj = session.getAttribute(SharedAttributes.CREDENTIAL);
		 
		 if(obj == null) { // 신원정보(=Credential)가 없다!!!
			 // 결론: 접근불가!!!
			 return false;
		 } else { // 금고상자안에 비록 지정된 이름으로 어떤 객체가 들어있기는
			 	  // 하나, 타입까지 Credential 객체인지 확인해야 한다!!
		 
			 if(obj instanceof Credential credential) { // 신원정보가 맞다!
				 String username = credential.getUsername();
				 String role = credential.getRole();
				 
				 if((username != null && !"".equals(username)) &&
					(role != null && !"".equals(role)) ) {
					return true;	// 결론: 접근가능(통과시키자!!)
				 } else {
					return false;	// 결론: 접근불가(통과못시킴!)
				 } // if-else
			 } else { 	// 신원정보(=Credential) 아님!
				 return false;	// 결론: 접근불가!!(통과 못시킴)
			 } // if-else
			 
		 } // if-else
	} // checkAuthentication

} // end class
