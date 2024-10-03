<%@ page 
		language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>

<%@page
    import="java.util.Date"
    import="java.util.Calendar"
    import="java.util.List" %>

<%  // Scriptlet Tag : 자바 실행문장(조각)들을 실행
    Date now = new Date();
%>

<h1>표현식 출력 실습</h1>
<h2>1. 변수: <%= now %></h2> 
<h2>2. 리터럴: <%= true %></h2> 
<h2>3. 연산식: <%= 100 + 200 %></h2>   
<h2>4. 메소드호출: <%= mul(10, 20) %></h2>   

<%! 
	public int mul(int number1, int number2) {
		return number1 * number2;
	} // mul
%>





    
    