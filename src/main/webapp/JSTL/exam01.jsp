<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h3><%= request.getRequestURI() %></h3>
<hr>

<h4>JSTL Core 라이브러리 실습#1</h4>

<hr>

<!-- set 태그의 목적: 공유영역에 속성 바인딩 수행 -->
<!-- var 속성: 바인딩되는 속성의 이름지정 -->
<!-- value 속성: 바인딩되는 속성의 값 -->
<!-- 중요: 이 태그의 var속성의 값이 EL변수명이 된다!!! -->

<c:set var="__PAGE__"           value="VALUE_1" scope="page"/>
<c:set var="__REQUEST__"        value="VALUE_2" scope="request"/>
<c:set var="__SESSION__"        value="VALUE_3" scope="session"/>
<c:set var="__APPLICATION__"    value="VALUE_4" scope="application"/>

 <!-- out 태그의 value 속성의 값: (1) 문자열 or (2) EL표기법 -->
 1. <c:out value="Hello World" /><br>
 2. <c:out value="${ __PAGE__ }" /><br>
 3. ${ __PAGE__ }<br>


<h4>JSTL Core 라이브러리실습#2</h4>

<hr>

<%@page import="org.zerock.myapp.entity.LoginBean" %>

<%
	LoginBean myBean = new LoginBean();
	// myBean.setMyUserid("Yoseph");
	// myBean.setMyPasswd("1234");

	// 이 현재 JSP파일의 수행이 끝날때까지만 유효한 page scope에 공유속성으로 바인딩
	pageContext.setAttribute("myBean", myBean);
%>

<!-- 4개의 공유영역에 한군데에 정한 이름으로 정한 값을 바인딩 수행 -->

<!-- 아래의 set 태그는, 공유영역에 속성 바인딩을 수행하는 것이 아니라!
이미 공유영역에 바인딩 되어잇는 자바빈즈 객체의 특정 프로퍼티의 값을
설정(set) 하는 태그역할을 수행: 
	
	myBean.setMyUserid("Yoseph");
-->
<c:set target="${ myBean }" property="myUserid" value="Trinity"/>

4. ${ myBean.myUserid }<br>
5. <c:out value="${ myBean.myUserid }" /> 


<h4>JSTL Core 라이브러리실습#3</h4>

<hr>

1. 삭제전: <c:out value="${ __REQUEST__ }" /><br>

<%-- request.removeAttribute("__REQUEST__"); --%>
<c:remove var="__REQUEST__"/>

2. 삭제후: <c:out value="${ __REQUEST__ }" /><br>




        