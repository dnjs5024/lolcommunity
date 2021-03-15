package com.example.demo.community.vo;


import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("TagTableVO")
public class TagTableVO {
	private int tagNum;
	private int freeNum;
	private int guideNum;
	private String tagContent;
	private String userNick;
	private String tagJoin;
	private String tagModifi;
	private int userNum;
	private String ghostPwd;
	private PageVO page;
	private String freeTitle;
	private String GuideTitle;
	private int guideTagNum;
	private int currentPageNo;
	
}
