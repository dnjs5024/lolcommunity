package com.example.demo.riot.service;

import java.util.List;
import java.util.Map;

import com.example.demo.riot.vo.SummonerInfoVO;

public interface SummonerInfoService {
	SummonerInfoVO searchSummonerInfo(String summoner);
	List<List<Map<String,Object>>> getGamePage(String userName,int pageNo);
	void parseGames(String summoner);
}
