package com.example.demo.community.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("PageVO")
public class PageVO {
	private int currentPageNo;//현재페이지
	private int lastPageNo;//마지막번호
	private int startPageNo;//시작번호
	private int pageRange;//페이지범위
	private int listRange;//한페이지에 보여질 게시물 수
	private int listSize;//총게시물수
	private int endPageNo;//아예마지막번호
	private int startRow;//첫게시물번호
	private int endRow;//마지막게시물번호

}
