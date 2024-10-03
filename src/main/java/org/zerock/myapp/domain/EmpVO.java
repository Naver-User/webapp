package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Value;


//@Data		// DTO 패턴대로 클래스를 만들고자 할 때 사용 (수정가능한 객체)
@Value		// VO 패턴대로 클래스를 만들고자 할 때 사용 (수정불가능한 객체)
public class EmpVO implements Serializable { // POJO : Plain Old Java Object.
	@Serial private static final long serialVersionUID = 1L;
	
	// 아래의 각 필드의 타입과 이름은, DB의 테이블에 스키마와 동일하게 선언
	// (주의사항) 필드의 타입은 기본타입을 쓰지말라! -> 
	//            왜? 테이블의 컬럼은 NULL을 저장가능 -> 자바타입에서는 기본타입으로
	//            NULL을 저장할 수 없기 때문에, 기본타입에 대응되는 Wrapper 타입으로
	//            필드 선언해야 함
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hireDate;		// 컬럼의 이름: hire_date
	private Double sal;
	private Double comm;
	private Integer deptno;	
	
		

	
} // end class

