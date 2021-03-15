package com.example.demo.community.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("FreeCntVO")
public class FreeCntVO {
	private String sessionKey;
	private FreeBoardVO freeBoard;

}
