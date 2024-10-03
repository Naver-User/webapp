package org.zerock.myapp;

public class Outer {

	
	public static void main(String[] args) {
//		(1) 객체생성
		Person person = new Person();
		
//		(2) Getter 메소드를 이용해서 필드값 출력
		
		// The field Person.name is not visible
//		System.out.println( person.name );
		System.out.println( person.getField1() );
		// get + field1 => getField1 (*********)
		
		// The field Person.age is not visible
//		System.out.println( person.age );
		System.out.println( person.getField2() );
		// get + field2 => getField2 (*********)
	} // main

} // end class

