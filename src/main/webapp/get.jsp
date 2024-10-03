<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>  
     
<%@ page isELIgnored="false" %>    
    
<h3><%= request.getRequestURI() %></h3>
<hr>    
	<h1>List 실습</h1>
	
	<%-- 
		첫번째 - 리스트의 각요소를 EL표현식으로 출력
		이때, 리스트는 인덱스번호로 요소접근이 가능하니, EL표현식 내에서
		인덱스 연산자인 []과 요소의 인덱스 번호로 출력가능 
						  --------- ---------------------- ------------------
						  공유속성명[리스트요소인덱스번호].자비빈의프로퍼티명
	--%>
	A. List 자체 출력: ${ __MODEL__ } <br>
	B. List 의 특정요소 출력: ${ __MODEL__.get(0) } <br><br>
	
	1. 1st. LoginBean: <br>
		1-1. ${ __MODEL__[0].myUserid } <br> 
		1-2. ${ __MODEL__[0]["myUserid"] } <br>
		1-3. ${ __MODEL__[0].myPasswd } <br>
		
	2. 2nd. LoginBean: ${ __MODEL__[1].myUserid }, ${ __MODEL__[1].myPasswd } <br>
	
	<%-- 두번째 - 표워드할때 같이 넘어온 전송파라미터를 EL표현식으로 출력 --%>
    3. name : ${ param.name } <br>
    4. age  : ${ param.age }
</body>
</html>