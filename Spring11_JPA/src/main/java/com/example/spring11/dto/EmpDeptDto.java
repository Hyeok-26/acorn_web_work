package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Dept;
import com.example.spring11.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpDeptDto {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private double sal;
	private double comm;
	//Emp 와 Dept 에 같이 있는 정보
	private int deptno;
	//Dept Entity 에만 있는 정보
	private String dname;
	private String loc;
	
	//ㄸEntity 를 dto 로 면경해서 리턴한ㄴ Static 메소드
	public static EmpDeptDto toDto(Emp emp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//2025.03.04 화 오후 3:10:25 형식의 문자열을 얻어낼 수 있는 객체이다.
		String hiredate=sdf.format(emp.getHiredate());
		
		return EmpDeptDto.builder()
				.empno(emp.getEmpno())
				.ename(emp.getEname())
				.job(emp.getJob())
				.mgr(emp.getMgr()==null ? 0 :emp.getMgr())
				.hiredate(hiredate)
				.sal(emp.getSal())
				.comm(emp.getComm()==null ? 0 :emp.getComm())
				.deptno(emp.getDept().getDeptno())
				.dname(emp.getDept().getDname())
				.loc(emp.getDept().getLoc())
				.build();
	}
}
