package com.example.spring11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring11.entity.Emp;
import com.example.spring11.entity.Member;

public interface EmpRepository extends JpaRepository<Emp, Integer>{
	//사원이름에 대해서 오름차순 정렬된 결과를 리턴하는 메소드 추가'
	public List<Emp> findAllByOrderByEnameAsc();
	
	@Query("""
		SELECT e,d
		FROM Emp e
		JOIN Dept d ON e.dept.deptno = d.deptno 
	""")
	public List<Emp> getList();
	
	
	
}
