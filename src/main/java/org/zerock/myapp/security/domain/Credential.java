package org.zerock.myapp.security.domain;

import lombok.Value;


@Value

// Session Scope(=금고상자)안에 넣을,
// 인증에 성공한 사용자정보를 가지고 있는 객체이다!
// 이를 보안에서는 Credential 이라 부른다!!
public class Credential {
	private String username;
	private String role;
	

} // end class

