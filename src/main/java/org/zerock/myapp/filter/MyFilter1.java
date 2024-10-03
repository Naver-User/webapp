package org.zerock.myapp.filter;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class MyFilter1 extends HttpFilter {       
	@Serial private static final long serialVersionUID = 1L;
	

    @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {	
    	log.trace("doFilter(req, res, chain: {}) invoked.", chain);
    	
    	// ---------------------------------------------------------
		// 요청을 필터링 하는 전처리(Pre-processing) 코드는 여기에 넣으세요.
    	// ---------------------------------------------------------
    	log.info("\t>>> (1) Pre-processing ...");
    	
    	
		chain.doFilter(req, res);

    	// ---------------------------------------------------------
		// 응답을 필터링 하는 후처리(Post-processing) 코드는 여기에 넣으세요.
    	// ---------------------------------------------------------
    	log.info("\t<<< (2) Post-processing ...");
		
	} // doFilter

} // end class
