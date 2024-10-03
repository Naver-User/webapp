<%@page
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


<h3>/loginInfo.jsp</h3>
<hr>

<%
    String userid = (String) session.getAttribute("userid");
    String passwd = (String) session.getAttribute("passwd");

    if( userid != null && passwd != null ) {
%>
      <h2><a href="/JSP/logout.jsp">로그아웃</a></h2>
<%
    } else {
        response.sendRedirect("/LoginForm.html");
        
        return;
    } // if-else
%>
