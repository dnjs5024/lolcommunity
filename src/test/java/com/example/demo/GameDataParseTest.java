package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.riot.component.RiotData;
import com.example.demo.riot.mapper.SummonerInfoMapper;
import com.example.demo.riot.vo.SummonerInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class GameDataParseTest {
	@Autowired
	RiotData rd;
	@Resource
	SummonerInfoMapper sim;
	
	public void gamePlayList(String tier,String rank) {
		String url = "https://kr.api.riotgames.com/lol/league/v4/entries/RANKED_SOLO_5x5/"+tier+"/"+rank+"?page=1";
		String test = rd.getReadData(url);
		ObjectMapper om = new ObjectMapper();
		List<Map<String,Object>> list = new ArrayList<>();
		SummonerInfoVO siv = new SummonerInfoVO();
		try {
			list = om.readValue(test, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log.info("list=>{}",list);
		
		for(Map<String,Object> rMap : list) {
			siv.setSummonerInfoId(rMap.get("summonerId").toString());
			siv.setSummonerInfoLosses(Integer.parseInt(rMap.get("losses").toString()));
			siv.setSummonerInfoName(rMap.get("summonerName").toString());
			siv.setSummonerInfoPoint(Integer.parseInt(rMap.get("leaguePoints").toString()));
			siv.setSummonerInfoRank(rMap.get("rank").toString());
			siv.setSummonerInfoTier(rMap.get("tier").toString());
			siv.setSummonerInfoWins(Integer.parseInt(rMap.get("wins").toString()));
			log.info("siv=>{}",siv);
			sim.insertSummonerInfo(siv);
		}
		
	}
	
	@Test
	public void test() {
		Map<String, List<String>> tier = new HashMap<>();
		List<String> rank = new ArrayList<>();
		rank.add("I");
		rank.add("II");
		rank.add("III");
		rank.add("IV");
		tier.put("IRON",rank);
		tier.put("BRONZE",rank);
		tier.put("SILVER",rank);
		tier.put("GOLD",rank);
		Iterator<String> it = tier.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			List<String> r = tier.get(key);
			for(String str : r) {
				log.info(key+"=>{}",str);
				gamePlayList(key,str);
			}
		}

	}
	
}
