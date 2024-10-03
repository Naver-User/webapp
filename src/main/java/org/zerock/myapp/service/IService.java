package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IService { // 모든 서비스의 규격 선언
	
	public abstract
	void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
} // end interface
