<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>

<h3><%= request.getRequestURI() %></h3>
<hr>

    
<h3>발생된 예외: <%= exception.getMessage() %></h3>  

  