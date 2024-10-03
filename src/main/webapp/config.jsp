<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>/config.jsp</h3>
<hr>

<h3>config 내장객체를 이용한, 초기화파라미터 사용법</h3>

<%
	String param1 = config.getInitParameter("PARAM1");
	String param2 = config.getInitParameter("PARAM2");
%>

<p>PARAM1: <%= param1 %></p> <br>
<p>PARAM2: <%= param2 %></p> <br>

    
