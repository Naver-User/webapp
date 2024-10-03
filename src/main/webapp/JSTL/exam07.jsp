<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Date" %>    
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    


<h3><%= request.getRequestURI() %></h3>
<hr>

<c:set var="myDate" value="<%= new Date() %>" scope="request" />
<c:out value="${myDate}" /><br>

<!-- ===================== -->

0. now: ${myDate}<br>
1. <c:out value="${ myDate.getClass().getName() }" /><br>

2. <fmt:formatDate value="${myDate}" type="date"/><br>
3. <fmt:formatDate value="${myDate}" type="time"/><br>    
4. <fmt:formatDate value="${myDate}" type="both"/><br>

5. <fmt:formatDate value="${myDate}" type="both" dateStyle="short" timeStyle="long"/><br>
6. <fmt:formatDate value="${myDate}" type="both" dateStyle="long" timeStyle="short"/><br>

7. <fmt:formatDate value="${myDate}" pattern="yyyy/MM/dd HH:mm:ss.SSS" /><br>



        