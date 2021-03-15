package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.SummonerInfoVO;

@Mapper
public interface SummonerInfoMapper {
	int insertSummonerInfo (SummonerInfoVO summoner);
	List<SummonerInfoVO> selectSummonerInfoList (SummonerInfoVO summoner);
	SummonerInfoVO selectSummonerInfo (String summoner);
	int updateSummonerInfo (SummonerInfoVO summoner);
}
