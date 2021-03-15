package com.example.demo.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.FreeCntVO;
import com.example.demo.community.vo.GuideCntVO;

@Mapper
public interface GuideCnt {
	int guideAddCnt(GuideCntVO cnt);
	int selectAddCnt(GuideCntVO cnt);
	int deleteGuideCnt();
}
