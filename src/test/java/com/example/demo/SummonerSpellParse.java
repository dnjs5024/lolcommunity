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
import com.example.demo.riot.mapper.SummonerSpellMapper;
import com.example.demo.riot.vo.SummonerSpellVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SummonerSpellParse {

	@Autowired
	RiotData rd;
	@Resource
	SummonerSpellMapper ssm;

	@Test
	public void timesStemp() {
		String test = rd.getStaticReadData("http://ddragon.leagueoflegends.com/cdn/10.22.1/data/ko_KR/summoner.json");
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rMap = new HashMap<>();
		try {
			rMap = om.readValue(test, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Map<String, Object> data = (Map<String, Object>) rMap.get("data");
		Iterator<String> it = data.keySet().iterator();
		List<SummonerSpellVO> spellList = new ArrayList<>();
		while(it.hasNext()) {
			String key = it.next();
			Map<String, Object> spell = (Map<String, Object>) data.get(key);
			List<String> modes = (List<String>)spell.get("modes");
			for(String mode : modes) {
				if(mode.equals("CLASSIC")) {
					SummonerSpellVO ssv = new SummonerSpellVO();
					ssv.setSummonerSpellId(spell.get("id").toString());
					ssv.setSummonerSpellName(spell.get("name").toString());
					ssv.setSummonerSpellDesc(spell.get("description").toString());
					ssv.setSummonerSpellKey(Integer.parseInt(spell.get("key").toString()));
					spellList.add(ssv);
					break;
				}
			}
		}
		for(SummonerSpellVO ssv : spellList) {
			ssm.insertSummonerSpell(ssv);
			log.info("ssv=>{}",ssv);
		}
		
	}
}
