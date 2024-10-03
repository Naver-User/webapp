<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<h3><%= request.getRequestURI() %></h3>
<hr>

<!-- ================================ -->

<h4>JSTL Core 라이브러리 실습#1</h4>

<hr>

<%-- pageContext.setAttribute("myColor", "빨강"); --%>
<c:set var="myColor" value="빨강" scope="request"/>

<c:if test='${ myColor == "빨강" }'>
    <p>색상은 빨강색이다.</p>        
</c:if>


<!-- ================================ -->

<h4>JSTL Core 라이브러리실습#2</h4>
<hr>

<!-- pageContext.setAttribute("grade", "70") -->
<!-- grade 란 EL변수(=공유속성의 이름) 생성됨 -->

<c:set var="grade" value="70" scope="session"/>
<!-- 자바의 switch문 또는 SQL의 CASE~WHEN문장을 JSTL을 이용해 구현 -->
<%-- 3개의 JSTL 태그를 이용해서 구현: <c:choose/>, <c:when/>, <c:otherwise/> --%>

<c:choose>
    <c:when test="${ grade >= 90 }">
        학점은A 이다.
    </c:when>

    <c:when test="${ grade >= 80 }">
        학점은B 이다.
    </c:when>

    <c:when test="${ grade >= 70 }">
        학점은 C(${grade}) 이다.<br>
        <c:remove var="grade" />
    </c:when>

    <c:otherwise>
        학점은F 이다.
    </c:otherwise>
</c:choose>



        