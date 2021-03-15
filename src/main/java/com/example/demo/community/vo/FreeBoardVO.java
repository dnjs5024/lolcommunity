package com.example.demo.community.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("FreeBoardVO")
public class FreeBoardVO {
	private boolean like;
	private Integer userNum;
	private String userNick;
	private Integer freeNum;
	private String freeTitle;
	private String freeContent;
	private String freeFilepath;
	private String freeFilename;
	private String freeJoin;
	private String freeModifi;
	private Integer freeCnt;
	private MultipartFile freeFile;
	private PageVO page;
	private Integer tagCnt;
	private Integer likeCnt;
	private String type;
	private String keyword;

	
}
