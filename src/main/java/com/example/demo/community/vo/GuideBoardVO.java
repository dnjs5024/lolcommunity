package com.example.demo.community.vo;

import org.apache.ibatis.type.Alias;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
@Alias("GuideBoardVO")
public class GuideBoardVO {
	private boolean like;
	private Integer userNum;
	private String userNick;
	private Integer guideNum;
	private String guideTitle;
	private String guideContent;
	private String guideFilepath;
	private String guideFilename;
	private String guideJoin;
	private String guideModifi;
	private Integer guideCnt;
	private MultipartFile guideFile;
	private PageVO page;
	private Integer tagCnt;
	private Integer likeCnt;
	private String type;
	private String keyword;
}
