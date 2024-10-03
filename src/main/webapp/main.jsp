<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>헤더제목</h1>

<p>문장</p>    

<%-- Static Include : 소스 그대로 포함시키자! --%>
<%-- @include file="copyright.jsp" --%>

<%-- Dynamic Include : 수행시키고, 그 결과를 포함시키자!! --%>
<jsp:include page="copyright.jsp" />

