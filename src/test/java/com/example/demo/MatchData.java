package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.riot.component.RiotData;
import com.example.demo.riot.mapper.MatchInfoMapper;
import com.example.demo.riot.mapper.SummonerInfoMapper;
import com.example.demo.riot.vo.MatchInfoVO;
import com.example.demo.riot.vo.SummonerInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MatchData {
	
	@Autowired
	private RiotData rd;
	@Resource 
	private SummonerInfoMapper sim;
	@Resource
	private MatchInfoMapper mim;
	
	public void matchGame(String id, String tier) {
		String encryptedSummonerId = id;
		// summoner-v4: summonerId로 소환사 정보(puuid) 조회
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/"+encryptedSummonerId;
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> rMap = new HashMap<>();
		try {
			rMap = om.readValue(rd.getReadData(url), Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if(rMap.get("puuid") == null) {
			return;
		}
		String puuid = rMap.get("puuid").toString();
		log.info(puuid);
		// match-v5: puuid로 매치 ID 목록 조회 (regional routing: asia)
		url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?queue=420";
		try {
			List<String> matchIds = om.readValue(rd.getReadData(url), List.class);
			if(matchIds == null) {
				return;
			}
			for(String matchId : matchIds) {
				MatchInfoVO match = new MatchInfoVO();
				match.setMatchId(matchId);
				match.setMatchTier(tier);
				mim.insertMatchInfo(match);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws InterruptedException {
		List<SummonerInfoVO> sList =  sim.selectSummonerInfoList(null);//match_info data 뽑아오는 로직
		int cnt = 1;
		for(SummonerInfoVO summoner : sList) {
			if(!summoner.getSummonerInfoTier().equals("IRON")) {//데이터분류 (티어)
				continue;
			}
			Thread.sleep(2500); //요청 데이터 딜레이 
			if(cnt>100) {
				break;
			}
			log.info("cnt==>{}",cnt++);
			matchGame(summoner.getSummonerInfoId(),summoner.getSummonerInfoTier());
		}
		
		
	}
	
}
