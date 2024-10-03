package org.zerock.myapp.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.service.AuthenticateService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/authenticate")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service({}, {}) invoked.", req, res);
		 
		 try {	// 보안의 첫번째 단계: 인증 확인

			 // -----------------
			 // Step1. 핵심로직: 금고상자(=Session Scope)안에, 신원정보(Credential)가 있느냐!?
			 
			 HttpSession session = req.getSession();
			 
			 AuthenticateService service = new AuthenticateService();
			 boolean isAuthenticated = service.isAuthenticationPermitted(session);			 
			 
			 // -----------------
			 // Step2. 인증확인 결과를, 포워드시킨 이전 서블릿에
			 //        Request Scope의 공유속성으로 되돌려 주자!!
			 
			 req.setAttribute(SharedAttributes.IS_AUTHENCATED, isAuthenticated);			 

			 // -----------------
			 // Step3. Step2의 인증확인결과(isAuthenticated)에 따라,
			 //        true인 경우 - 아래 화면을 만들어 보내주고
		     //		   false인 경우 - 강제로 로그인 화면으로 밀어버립니다.
			 
			 if(!isAuthenticated) { // 로그인화면으로 강제이동
				 res.sendRedirect("/login");
			 } // if
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

