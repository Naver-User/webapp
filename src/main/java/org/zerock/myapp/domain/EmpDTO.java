package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


// 전송파라미터를 저장하는 용도의 클래스로
// 웹3계층에서, 어느 계층에서든, 전송파라미터 데이터가 필요한 곳에서
// 사용가능합니다.

@Data
public class EmpDTO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
//	         자바타입 자바식별자규칙에맞게선언(복합단어의 컬럼 -> 카멜적용)
//	자바빈즈 컬럼타입 컬럼명
//	-------  -------  ------
	private  Integer  empno;
	
	
	private String ename;
	private String job;
	private Integer mgr;
	
	private Date hireDate;		// 컬럼명 -> hire_date
	private Double sal;
	private Double comm;
	private Integer deptno;
	
		

	
} // end class

