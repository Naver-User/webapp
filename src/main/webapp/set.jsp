<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>   
    
    
<h3><%= request.getRequestURI() %></h3>
<hr>

<%@page
	import="org.zerock.myapp.entity.LoginBean"
	import="java.util.List"
	import="java.util.ArrayList" %>    
        
<h1>List 실습</h1>

<%
	// Step1. 2개의 자바빈즈 객체 생성 - 전송파라미터를 받아서 빈객체를 만든다고 가정
    LoginBean oneBean = new LoginBean();
    oneBean.setMyUserid("aaa");
    oneBean.setMyPasswd("1234");

    LoginBean twoBean = new LoginBean();
    twoBean.setMyUserid("bbb");
    twoBean.setMyPasswd("2222");

    // Step2. 리스트 객체를 만들고, 2개의 자바빈즈(LoginBean) 객체를 요소로 추가
    List<LoginBean> list = new ArrayList<>();
    
    list.add(oneBean);
    list.add(twoBean);

    // Step3. Request Scope 에 리스트 객체를 속성으로 바인딩
    request.setAttribute("__MODEL__", list );
%>

<%--
아래의 JSP표준액션태그는, 유일하게 현재에도 간혹 사용되는 표준태그입니다.
JSP에서 표준으로 제공되는 태그를 => JSP 표준액션태그라고 부릅니다.
아래의 JSP 표준액션태그의 기능은, Request Forwarding 입니다.

추가적인 전송파라미터 함께 전송
--%>
<jsp:forward page="/get.jsp">
    <jsp:param name="name" value="Yoseph" />
    <jsp:param name="age" value="23" />
</jsp:forward>

</body>
</html>