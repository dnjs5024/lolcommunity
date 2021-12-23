package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.MatchGameInfoVO;
import com.example.demo.riot.vo.MatchItemSlotVO;
import com.example.demo.riot.vo.SummonerInfoVO;

@Mapper
public interface MatchGameInfoMapper {
	int insertMatchGameInfo (MatchGameInfoVO matchGame);
	List<MatchItemSlotVO> selectMatchGameInfoList (MatchGameInfoVO matchGame);
	List<MatchGameInfoVO> selectMatchGameId (String matchGameId);
	List<MatchGameInfoVO> selectMatchGameListById (SummonerInfoVO summoner);
	int totalMatchGameByName(String summoner);
	
	
}
