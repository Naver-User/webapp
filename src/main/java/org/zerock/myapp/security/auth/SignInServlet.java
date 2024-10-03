package org.zerock.myapp.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.domain.LoginDTO;
import org.zerock.myapp.security.domain.UserVO;
import org.zerock.myapp.security.service.SignInService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {	// 로그인 처리(인증처리) 수행
			 // 비지니스(=서비스) 계층의 서비스 객체생성 및 
			 // 인증처리 메소드 호출 with DTO
			 
			 // Step1. 로그인 전송파라미터 획득
			 String username = req.getParameter("username");
			 String password = req.getParameter("password");
			 
			 // Step2. Step1에서 얻어낸 모든 전송파라미터를 저장하는
			 //        DTO 생성
			 LoginDTO dto = new LoginDTO();
			 dto.setUsername(username);
			 dto.setPassword(password);
			 
			 // Step3. 비지니스(=서비스) 계층의 메소드 호출 with DTO
			 SignInService service = new SignInService();
			 boolean isAuthenticated = service.loginProcess(dto);
			 
			 // Step4. 인증에 성공했으면, Session Scope(=브라우저마다 하나씩
			 //        만들어지는 금고상자)안에, 인증성공한 상용자정보(=Credendial)
			 //        를 생성해서, Session Scope에 공유속성으로 저장
			 if(isAuthenticated) {	// 인증성공시에
				 // 인증 성공한 사용자정보(= Credential) 객체 생성
				 // Session Scope(=금고상자)에 공유속성으로 저장
				 UserVO vo = service.findByUserName(dto);
				 
				 // 인증에 성공한 사용자정보(즉, Credential)을 생성해서
				 // 금고상자(Session Scope)에 넣어둡니다.
				 Credential credential = 
					 new Credential(dto.getUsername(), vo.getRole());
				 
				 // Session Scope(=금고상자)에 공유속성으로 저장
				 HttpSession session = req.getSession(false);
				 
				 if(session != null) { // 금고상자가 있으면
					 session.setAttribute(SharedAttributes.CREDENTIAL, credential);
				 } else { // 금고상자가 없다면 -> 부적절한 상태
					 throw new IllegalStateException("No Session Scope.");
				 } // if
				 
				 // 로그인 처리 성공완료했으면, 메인화면으로 이동
				 // Re-direction 을 이용해서, 강제로 화면전환시킴
				 res.sendRedirect("/main");
			 } else {				// 인증실패시에 => 다시 로그인 화면으로 강제이동
				 // Re-direction 을 이용해서, 강제로 화면전환시킴
				 res.sendRedirect("/login");	// 로그인 화면으로 이동
			 } // if-else
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

