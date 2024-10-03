<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h3><%= request.getRequestURI() %></h3>
<hr>

<!-- ====================================== -->

<h4>JSTL Core 라이브러리 실습#1</h4>

<hr>

<%  // scriptlet tag
   int [] num = { 1,2,3,4,5,6,7,8,9,0 };

   // request scope 에 배열객체 바인딩
   request.setAttribute("myArray", num);
   
   request.setAttribute("_YOSEPH_", "Yoseph");
%>

<!-- 자바의 enhanced for 문과 비슷하게 반복수행 -->
<%-- 
  중요사항: 보통 대부분의 EL변수란, 공유속성의 이름을 의미하는데요.
          	예외적으로 아래와 같이, 반복문 OR <c:set var=...> 태그와 
          	같이, core tag 가 만들어내는 EL변수도 있습니다.
          	결국 공유속성이 아니래도, ${EL변수명}으로 사용가능하게 됩니다.
--%>
<c:forEach var="element" items="${myArray}">
   <%-- <c:out value="${element}" /> --%>
   ${element}
</c:forEach>


<!-- ====================================== -->
<h4>JSTL Core 라이브러리실습#2</h4>

<hr>

<%
	// page directive 에 import 속성으로 List의 타입명을 기재하지 않을거면,
	// 아래와 같이 개발자가 직접 FQCN으로 객체를 생성해도 됨
	java.util.List<String> list = new java.util.ArrayList<>();	// @since 8

	list.add("홍길동");
	list.add("이순신");
	list.add("유관순");
	
	// Request Scope 에 공유객체로 list 객체를 바인딩함
	// 이때, 공유속성의 이름은 EL변수명이 된다!
	request.setAttribute("__NAME__", list);
%>

<c:forEach var="element" items="${__NAME__}">	
	${element} <br>	
</c:forEach>

<!-- ====================================== -->
<h1>JSTL Core 라이브러리실습#3</h1>

<hr>

<ul>
	<c:forEach var="counter" begin="0" end="9" step="1">
		<li>${counter}</li>
	</c:forEach>	
</ul>

<!-- ====================================== -->
<h1>JSTL Core 라이브러리실습#4</h1>

<hr>

<%
	// CSV : Comma(,) Separated Value
	String str = "A,   B,     C,D";	// 쉼표(,)로 구분되어 있는 문자열(CSV형식의 문자열)

	// request scope에 바인딩: "data"란 이름이, EL변수명이 된다!
	request.setAttribute("data", str);
%>

<c:forTokens var="token" items="${ data }" delims=", " >
	TOKEN(${token}) <br>
</c:forTokens>








        