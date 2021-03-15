package com.example.demo.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.FreeCntVO;

@Mapper
public interface FreeCnt {
	int freeAddCnt(FreeCntVO cnt);
	int selectAddCnt(FreeCntVO cnt);
	int deleteFreeCnt();
}
