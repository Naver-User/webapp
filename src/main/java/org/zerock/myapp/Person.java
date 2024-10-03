package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor
public class Person {
	// 결론: 클래스의 각 필드이름과,
	//       이 각 필드에 대한 Getter/Setter 이름은
	//       근원적으로 다르다!!!
	//       즉, 각 필드에 대해서 2개의 이름이 생긴다
	//       그래서, 필드에 대한 Getter/Setter 이름을
	//       우리는 해당 필드에 대한 "프로퍼티(Property)"
	//       라고 부른다!!!
	private String name; // -> getName
	private int age;     // -> getAge
	
	
	public String getField1() {
		return this.name;
	}
	public String getName() {
		return this.name;
	}

	public int getField2() { // => "field2"
		return this.age;
	}
	
	public int getAge() { // => "age"
		return this.age;
	}
	
	// ----- main -------- //
	public static void main(String... args) {
//		(1) 객체생성
		Person person = new Person();
		
//		(2) Getter 메소드를 이용해서 필드값 출력
		System.out.println( person.name );
		System.out.println( person.age );
	} // main
	
} // end class
