<%@ page 
		language="java" 
		contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"  %>

<%@ page 
		import="java.util.Calendar"  
		import="java.util.Date" 
%>

<%@ page import="java.util.*" %>

<%-- JSP 만의 문법으로 여러타입을 import 할 때, 쉼표(,)로 구분해서
나열하는 아래와 같은 방법도 지원합니다. --%>
<%@ page 
		import="java.util.Calendar,java.util.Date"  
%>	    
	    
    


<%-- 
	현재시간이 오전 -> Good Morning! 출력
           오후 -> Good Afternoon! 출력
--%>          
    
<%	// Scriptlet tag1
	// (1) Calendar 타입에 대한 import 문이 필요
	Calendar cal = Calendar.getInstance();
	int result = cal.get(Calendar.AM_PM);	// 오전: 0, 오후: 1
	
	if(result == Calendar.AM) {
%>		
		Good Morning. 123456890. 31.159. true.
				
<%	// Scriptlet tag2	
	} else {
%>
		Good Afternoon. 123456890. 31.159. true.
					
<%	// Scriptlet tag3	
	} // if-else
%>           
	
	
	    

    
    
    