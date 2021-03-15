package com.example.demo.riot.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.mapper.RankChampMapper;
import com.example.demo.riot.service.RankChampService;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.RankChampVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RankChampServiceImpl implements RankChampService {
	
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
		rankInfo.put("kda",kda);
		
		int totalGame = champ.getTotalGame();
		int winGame = champ.getWinGame();
		double avgWin = ((winGame*1.0)/totalGame)*100;
		avgWin = Math.round(avgWin*10)/10.0;
		rankInfo.put("avgWin",avgWin);
		
		int allGame = rcm.selectRankMatchTotal(tierLine);
		double avgPick = ((totalGame*1.0)/allGame)*100;
		avgPick = Math.round(avgPick*10)/10.0;
		rankInfo.put("avgPick",avgPick);
		
		double score = kda*0.4 + (avgWin/10)*1.1 + avgPick*1.2;
		score = Math.round(score*10)/10.0;
		rankInfo.put("score",score);
		return rankInfo;
	}
	
	
	
	@Override
	public List<Map<String,Object>> selectRankChampList(Map<String, String> tierLine) {
		
		List<RankChampVO> champList = rcm.selectRankChampList(tierLine);
		List<Map<String,Object>> listRankInfo = new ArrayList<>();
		for(RankChampVO champ : champList) {
			Map<String,Object> rankInfo = parseRank(champ, tierLine);
			ChampionInfoVO champion = new ChampionInfoVO();
			champion.setChampionInfoKey(champ.getChampionInfoKey());
			champion = cim.selectChampionInfo(champion);
			rankInfo.put("champion", champion);
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
		return listRankInfo;
	}

}
