package org.zerock.myapp.security.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		 log.trace("service(req, res) invoked.");
		 
		 try {
			 res.setCharacterEncoding("utf8");
			 res.setContentType("text/html; charset=utf8");
			 
			 @Cleanup PrintWriter out = res.getWriter();
			 out.println("<h3>/user</h3>");
			 out.println("<hr>");
			 out.flush();
		 } catch(Exception original) {
			 throw new IOException(original);
		 } // try-catch
	} // service


} // end class

