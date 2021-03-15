package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.mapper.RankChampMapper;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.RankChampVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RankChampTest {
	
	@Resource
	private RankChampMapper rcm;
	@Resource
	private ChampionInfoMapper cim;
	
	public Map<String,Object> parseRank(RankChampVO champ, Map<String,String> tierLine) {
		int kills = champ.getKills();
		double deaths = champ.getDeaths();
		int assists = champ.getAssists();
		Map<String,Object> rankInfo = new HashMap<>();
		if(deaths == 0) {
			deaths = 0.9;
		}
		double kda = (kills+assists)*1.0/deaths;
		kda = Math.round(kda*10)/10.0;
		log.info("kda==>{}", kda);
		rankInfo.put("kda",kda);
		
		int totalGame = champ.getTotalGame();
		int winGame = champ.getWinGame();
		double avgWin = ((winGame*1.0)/totalGame)*100;
		avgWin = Math.round(avgWin*10)/10.0;
		log.info("avgWin==>{}", avgWin);
		rankInfo.put("avgWin",avgWin);
		
		int allGame = rcm.selectRankMatchTotal(tierLine);
		double avgPick = ((totalGame*1.0)/allGame)*100;
		avgPick = Math.round(avgPick*10)/10.0;
		log.info("avgPick==>{}", avgPick);
		rankInfo.put("avgPick",avgPick);
		
		double score = kda*0.4 + (avgWin/10)*1.1 + avgPick*1.2;
		score = Math.round(score*10)/10.0;
		log.info("score==>{}", score);
		rankInfo.put("score",score);
		return rankInfo;
	}
	
	@Test
	public void test() {
		Map<String,String> tierLine = new HashMap<>();
		tierLine.put("tier","BRONZE");
		tierLine.put("position","mid");
		List<RankChampVO> champList = rcm.selectRankChampList(tierLine);
		List<Map<String,Object>> listRankInfo = new ArrayList<>();
		for(RankChampVO champ : champList) {
			Map<String,Object> rankInfo = parseRank(champ, tierLine);
			ChampionInfoVO champion = new ChampionInfoVO();
			champion.setChampionInfoKey(champ.getChampionInfoKey());
			champion = cim.selectChampionInfo(champion);
			rankInfo.put("champion", champion);
			rankInfo.put("position", tierLine.get("position"));
			rankInfo.put("tier", tierLine.get("tier"));
			listRankInfo.add(rankInfo);
		}
		
		Collections.sort(listRankInfo, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String,Object> o1, Map<String,Object> o2) {
				Double bot1 = (Double) o1.get("score");
				Double bot2 = (Double) o2.get("score");
				return bot2.compareTo(bot1);
			}
		});
		log.info("listRankInfo==>{}",listRankInfo);
		
		
	}
}
