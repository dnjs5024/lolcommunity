package com.example.demo.riot.utill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.example.demo.riot.component.RiotData;
import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.ChampionSpellVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
	@Resource
	static ChampionInfoMapper cim;
	
	public static void main(String[] args) {
		PatternTest patternTest = new PatternTest();
		List<ChampionInfoVO> list = cim.selectChampionInfoList(null);
		System.out.println(list);
		ChampionInfoVO ci = new ChampionInfoVO();
		String championName = ci.getChampionInfoId();
		String urlStr = "http://ddragon.leagueoflegends.com/cdn/10.22.1/data/ko_KR/champion/"+championName+".json";
		RiotData riotData = new RiotData();
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> champ = om.readValue(riotData.getReadData(urlStr), Map.class);
			// print map entries
			Map<String, Object> champData = (Map<String, Object>) champ.get("data");
			Map<String, Object> champDataName = (Map<String, Object>) champData.get(championName);
			List<Map<String, Object>> champDataNameSpell =  (List<Map<String, Object>>) champDataName.get("spells");
			Map<String, Object> champDataNamePassive =  (Map<String, Object>) champDataName.get("passive");
			List<ChampionSpellVO> spellList = new ArrayList<>();
			char type = 'Q';
			int typeNum = 0;
			for(Map<String, Object> spellInfo : champDataNameSpell) {
				ChampionSpellVO championSpellVO = new ChampionSpellVO();
				championSpellVO.setChampionSpellId(spellInfo.get("id").toString());
				championSpellVO.setChampionSpellName(spellInfo.get("name").toString());
				championSpellVO.setChampionSpellCooldown(spellInfo.get("cooldownBurn").toString());
				if(!"0".equals(spellInfo.get("costBurn"))) {
					championSpellVO.setChampionSpellCost(spellInfo.get("costBurn").toString());
				}
				if(!"0".equals(spellInfo.get("rangeBurn"))) {
					championSpellVO.setChampionSpellRange(spellInfo.get("rangeBurn").toString());
				}
				championSpellVO.setChampionSpellDescription(patternTest.filter(spellInfo));
				if(typeNum==3) {
					championSpellVO.setChampionSpellType('R');
					typeNum++;
				}
				if(typeNum==2) {
					championSpellVO.setChampionSpellType('E');
					typeNum++;
				}
				if(typeNum==1) {
					championSpellVO.setChampionSpellType('W');
					typeNum++;
				}
				if(typeNum==0) {
					championSpellVO.setChampionSpellType('Q');
					typeNum++;
				}
				spellList.add(championSpellVO);
			}
			ChampionSpellVO championSpellVO = new ChampionSpellVO();
			championSpellVO.setChampionSpellName(champDataNamePassive.get("name").toString());
//			Pattern	pattern = Pattern.compile("<(.+?)>");
			String des = champDataNamePassive.get("description").toString();
//			Matcher	matcher = pattern.matcher(des);
//			while (matcher.find()) {
//				des = des.replace(matcher.group(0), "");
//			}
			championSpellVO.setChampionSpellDescription(des);
			String passiveName = ((Map<String, Object>) champDataNamePassive.get("image")).get("full").toString();
			championSpellVO.setChampionSpellId(passiveName.substring(0,passiveName.lastIndexOf(".png")));
			championSpellVO.setChampionSpellType('P');
			spellList.add(championSpellVO);
			for(ChampionSpellVO csv : spellList) {
				System.out.println(csv);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
