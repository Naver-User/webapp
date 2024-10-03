<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>    
<%@ page import="org.zerock.myapp.entity.LoginBean" %>    
    
    
<h3><%= request.getRequestURI() %></h3>
<hr>

<%
    request.setCharacterEncoding("utf8");

    String yourUserid = request.getParameter("yourUserid");
    String yourPasswd = request.getParameter("yourPasswd");

    LoginBean myBean = new LoginBean();
    myBean.setMyUserid(yourUserid);
    myBean.setMyPasswd(yourPasswd);
    
    System.out.println("---------- myBean ----------");
    System.out.println(myBean);
    
    pageContext.setAttribute("_MYBEAN_", myBean);	// Page Scope 에 공유속성 추가
%>

<h3>모델인 LoginBean 빈객체의 프로퍼티를 EL표현식으로 출력</h3>
<p>userid: ${ _MYBEAN_.getMyUserid() }</p>
<p>passwd: ${ _MYBEAN_.getMyPasswd() }</p>

<p>loginBean: ${ _MYBEAN_ }</p>


<h3>각 전송파라미터를 EL표현식을 통해서 출력해보자</h3>
<p>${ param.yourUserid }</p>
<p>${ param.yourPasswd }</p>
 



    