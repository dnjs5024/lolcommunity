package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.MatchInfoVO;
import com.example.demo.riot.vo.SummonerInfoVO;

@Mapper
public interface MatchInfoMapper {
	int insertMatchInfo (MatchInfoVO match);
	List<MatchInfoVO> selectMatchInfoList (MatchInfoVO match);
}
