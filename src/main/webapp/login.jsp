<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>   
    
    
<h3><%= request.getRequestURI() %></h3>
<hr>

<%
   // 4개의 각각의 공유영역에 공유속성 바인딩 예제
   pageContext.setAttribute("NAME_1", "VALUE_1");      // page
   request.setAttribute("NAME_2", "VALUE_2");          // request
   session.setAttribute("NAME_3", "VALUE_3");          // session
   application.setAttribute("NAME_4", "VALUE_4");      // application
%>

<%-- ${ EL각SCOPE별내장객체명.공유속성명 }을 통한, 공유객체의 출력 --%>
<h1>1. pageScope의 속성값은: ${ pageScope.NAME_1 }</h1>
<h1>2. requestScope 속성값은: ${ requestScope.NAME_2 }</h1>
<h1>3. sessionScope 속성값은: ${ sessionScope.NAME_3 }</h1>
<h1>4. applicationScope 속성값은: ${ applicationScope.NAME_4 }</h1>

<hr>

<%-- ${ 공유속성명 }을 통한, 공유객체의 출력 --%>
<%-- 이때 이 공유속성의 이름을, "EL변수"라고 합니다. --%>

<h1>1. pageScope의 속성값은: ${ NAME_1 }</h1>
<h1>2. requestScope 속성값은: ${ NAME_2 }</h1>
<h1>3. sessionScope 속성값은: ${ NAME_3 }</h1>
<h1>4. applicationScope 속성값은: ${ NAME_4 }</h1>

    