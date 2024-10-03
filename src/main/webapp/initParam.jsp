<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>   
    
    
<h3><%= request.getRequestURI() %></h3>
<hr>

	<h1>컨텍스트 초기화 파라미터 출력</h1>
	
	1. context 파라미터: ${ initParam.CONTEXT_PARAM1 }<br>
	2. context 파라미터: ${ initParam.CONTEXT_PARAM1 }<br>

    <%
        String name = "Yoseph";
    %>

    3. name : <%= name %>
</body>
</html>