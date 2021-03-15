package com.example.demo.community.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("LikeVO")
public class LikeVO {
	private int likeNum;
	private int freeNum;
	private int userNum;
	private int likeCheck;
	private int guideNum;

}
