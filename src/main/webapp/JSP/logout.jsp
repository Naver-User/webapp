<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<h3>/JSP/logout.jsp</h3>
<hr>


<%
    // Step.1 해당 브라우저에 할당된 Session Scope 파괴
    session.invalidate();

    // Step.2 로그아웃처리 후에, 웹브라우저는 어느 화면으로 이동되어야 할까!???
    response.sendRedirect("/LoginForm.html");   // Re-direct
%>
    
    