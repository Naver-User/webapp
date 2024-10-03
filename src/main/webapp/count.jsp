<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>/count.jsp</h3>
<hr>

<%! int count; %>

<%
    count++;

    application.setAttribute("countValue", count);
%>

<h2>Visitors: <%= count %></h2>