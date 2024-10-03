package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.ToString;


// 역할: DTO, 용도: MVC패턴의 Model(비지니스데이터) 

@ToString
@NoArgsConstructor
public class LoginBean implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String userid;
	private String passwd;
	
	
	
	// 2개의 프로퍼티(properties)의 이름: myUserid, myPasswd <--- ***
	public void setMyUserid(String myUserid) {
		this.userid = myUserid;
	} // setMyUserid
	
	public void setMyPasswd(String myPasswd) {
		this.passwd = myPasswd;
	} // setPasswd
	
	public String getMyUserid() {
		return this.userid;
	} // getMyUserid
	
	public String getMyPasswd() {
		return this.passwd;
	} // getMyPasswd
	
} // end class

