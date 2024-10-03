<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.ResourceBundle" %>    
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    


<h3><%= request.getRequestURI() %></h3>
<hr>

<%
	// 리소스 번들이라 불리는 파일(XXX.properties)에서, 
	// 지정된 항목의 값을 획득/출력
    String value = 
    	ResourceBundle
    		.getBundle("message")
    		.getString("sendMessage");        
%>

<%= value %><br>


<fmt:setBundle basename="message" var="myBundle" />    
<fmt:message key="sendMessage" bundle="${myBundle}" />

<h2>sendMessage: ${myBundle}</h2>








        