package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.RunePageVO;

@Mapper
public interface RunePageMapper {
	int insertRunePage (RunePageVO Rune);
	List<RunePageVO> selectRunePageList (RunePageVO Rune);
	RunePageVO selectRunePageByPage (RunePageVO Rune);
	
	
}
