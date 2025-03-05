package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//지금은 Setter 가 사용될 일은 없기 때문에 불필요 
@Getter
@Builder
@Entity
public class Emp {
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	//부서번호는 하나만 있어도 되기 때문에 주석 처리한다
	//private Integer deptno;
	/*
	 *  Emp객체 하나는 사원 한 명의 정보를 가지고 있다
	 *  Dept 객체 하나는 부서 하나의 정보를 가지고 있다.
	 *  Emp 객체 안에 있는 Dept 객체는 Emp 객체가 가지고 있는 해당 사원의 부서정보를 가지게 하고 싶다!!
	 	
	 	name="deptno" 는 Emp 테이블의 말럼명을 결정한다
	 	referencedColumnName = "deptno" Dept 테이블의 어떤 칼럼을 참조할 지 결정한다(생략 시 자동으로 @Id 칼럼 참조)
	 */
	@ManyToOne
	@JoinColumn(name="deptno", referencedColumnName = "deptno")
	private Dept dept;
}
