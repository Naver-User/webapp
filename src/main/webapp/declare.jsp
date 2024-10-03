<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<h3>/declare.jsp</h3>
<hr>

<p>Declaration 태그를이용한 JSP LifeCycle 메소드</p>

<%-- ===== 선언태그를 사용해보자!!! --%>

<%! 
    // (1) 변환될 서블릿 클래스 소스파일에 필드 선언
    private String initMesg="jspInit 메소드";		// 필드1
    private String delMesg="jspDestroy 메소드";		// 필드2
    
    public static final double PI = 3.14159;	// 정적필드!!!

    
    // (2) 변환될 클래스 소스파일에 메소드 선언
    //     JSP LifeCycle(서블릿과 비슷하게) 메소드

    public void jspInit() { // When NEW state, called back.
    	System.out.println(">>>>> jspInit() invoked.");
        System.out.println("\t+ initMesg: "+ initMesg);
    } // jspInit

    public void jspDestroy() {  // When DESTROY state, called back.
    	System.out.println(">>>>> jspDestroy() invoked.");
        System.out.println("\t+ delMesg: "+ delMesg);
    } // jspDestroy
    
    // (3) static initializer		-> OK
    static {
    	System.out.println("static initializer");
    } // static initializer
    
    // (4) 중첩 클래스 선언 		-> OK
    class NestedClass {;;}
    
    // (5) 중첩 인터페이스 선언 	-> OK
    interface NestedInterface {;;}
    
    // (6) 사용자정의블록 선언		-> OK
    {
    	// 정적필드의 값 출력
    	System.out.println("User-defined Block. PI = " + PI);
    }
%>



