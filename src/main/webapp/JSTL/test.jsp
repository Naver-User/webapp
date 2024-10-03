<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    


<%
	java.util.List<Integer> obj = java.util.Arrays.asList(1, 2, 3);		
	
	// 공유속성의 이름 == EL변수명 => ${ EL변수명 } 으로 사용가능하다!
	request.setAttribute("OBJ", obj);
%>

<c:forEach var="${ Yoseph }" items="${OBJ}">
								-------- (1) : 진짜 List 객체가 필요
	${ OBJ }	----- (2): 진짜 List객체가 필요한게 아니라,
						    List.toString() 의 결과(문자열)가 출력
</c:forEach>

(1) JSTL tag 의 특정속성에 ${표현식}이 나오면,
    이것은 객체자체를 해당 속성에 제공하겠다는 의미
    
(2) 이 자리에, 객체의 toString() 메소드의 결과값을 출력하는 의미    







        