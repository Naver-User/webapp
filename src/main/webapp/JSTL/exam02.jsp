<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h3><%= request.getRequestURI() %></h3>
<hr>

1. <c:out value="${ __PAGE__ }" /><br>
2. <c:out value="${ __REQUEST__ }" /><br>
3. <c:out value="${ __SESSION__ }" /><br>
4. <c:out value="${ __APPLICATION__ }" /><br>


        