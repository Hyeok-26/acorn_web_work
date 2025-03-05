package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Dept {
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
}
