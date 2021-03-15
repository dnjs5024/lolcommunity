package com.example.demo.riot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.RankChampVO;

@Mapper
public interface RankChampMapper {
	
	public List<RankChampVO> selectRankChampList(Map<String,String> tierLine);
	public int selectRankMatchTotal(Map<String,String> tierLine);
}
