package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.RuneInfoVO;

@Mapper
public interface RuneInfoMapper {
	int insertRuneInfo (RuneInfoVO rune);
	List<RuneInfoVO> selectRuneInfoList (RuneInfoVO rune);
	RuneInfoVO selectRuneInfo (int runeKey);
	
	
}
