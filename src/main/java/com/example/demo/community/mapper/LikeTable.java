package com.example.demo.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.LikeVO;

@Mapper
public interface LikeTable {
	int selectCheck(LikeVO vo);
	int insertCheck(LikeVO vo);
	int deleteCheck(LikeVO vo);
	int checkCount(LikeVO vo);
	int deleteAll(LikeVO vo);	
	int insertCheckGuide(LikeVO vo);
	int selectCheckGuide(LikeVO vo);
	int deleteCheckGuide(LikeVO vo);
	int checkCountGuide(LikeVO vo);
	int deleteGuideAll(LikeVO vo);	
}
