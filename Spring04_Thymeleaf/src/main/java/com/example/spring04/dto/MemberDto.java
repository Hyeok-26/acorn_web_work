package com.example.spring04.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
