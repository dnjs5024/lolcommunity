package com.example.demo.community.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("UserInfoVO")
public class UserInfoVO {
	private Integer userNum;
	private String userId;
	private String userNick;	
	private String userEmail;
	private String userPwd;
	private String userJoin;
	private String userModifi;
	private String userIcon;
	private PageVO page;
	private Integer currentPageNo;
	private Integer userLevel;
	private Integer userExperience;
	


}
