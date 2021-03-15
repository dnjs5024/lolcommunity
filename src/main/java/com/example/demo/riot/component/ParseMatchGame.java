package com.example.demo.riot.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.riot.mapper.MatchGameInfoMapper;
import com.example.demo.riot.mapper.MatchInfoMapper;
import com.example.demo.riot.mapper.MatchItemSlotMapper;
import com.example.demo.riot.mapper.RunePageMapper;
import com.example.demo.riot.vo.MatchGameInfoVO;
import com.example.demo.riot.vo.MatchItemSlotVO;
import com.example.demo.riot.vo.RunePageVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParseMatchGame {
	
	
	
	@Autowired
	private RiotData rd;
	@Resource
	private MatchInfoMapper mim;
	@Resource
	private MatchGameInfoMapper mgim;
	@Resource
	private RunePageMapper rpm;
	@Resource
	private MatchItemSlotMapper mism;
	
	private void sortValue(List<Map<String,Object>> list, String position) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer bot1 = (Integer) o1.get(position);
				Integer bot2 = (Integer) o2.get(position);
				return bot2.compareTo(bot1);
			}
		});
		
	}
	
	private List<MatchGameInfoVO> setPosition(long matchId) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/timelines/by-match/"+matchId;
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> rMap = new HashMap<>();
		try {
			rMap = om.readValue(rd.getReadData(url), Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Iterator<String> it = rMap.keySet().iterator();
		List<MatchGameInfoVO> participantPosition = new ArrayList<>();
		while(it.hasNext()) {
			String key = it.next();
			String value = rMap.get(key).toString();
			
			if(key.contains("frames")) {
				List<Map<String,Object>> list = (List<Map<String,Object>>)rMap.get("frames");
				Map<String, Object> m = list.get(list.size()-1);
				if(list.size() < 10) {
					log.info("10분이하 게임");
					return null;
				}
				int jungleSummoner1 = 0;
				int jungleSummoner2 = 0;
				List<Map<String,Object>> participantList = new ArrayList<>();
				for(int i=1; i<11; i++) {
					participantList.add(new HashMap<>());
					participantList.get(i-1).put("participantNum",i);
					participantList.get(i-1).put("jungle",0);
					participantList.get(i-1).put("top",0);
					participantList.get(i-1).put("mid",0);
					participantList.get(i-1).put("bot",0);
					participantList.get(i-1).put("none",0);
				}
				
				for(int i =1; i<11; i++) {
					Map<String,Object> map = new HashMap<>();
					map = list.get(i);
					Map<String,Object> participantFrames = (Map<String, Object>) map.get("participantFrames");
					
					for(int j =1; j<6; j++) {
						
						Map<String,Object> participant1 = new HashMap<>();
						Map<String,Object> participant2 = new HashMap<>();
						participant1 = (Map<String, Object>)participantFrames.get(j+"");
						participant2 = (Map<String, Object>)participantFrames.get((j-1)+"");
						 if(j==1) {
							 jungleSummoner1 = j;
						 }
						 
						 
						if((participant2 !=null && 
								(int)participant1.get("jungleMinionsKilled")<(int)participant2.get("jungleMinionsKilled"))) {
							jungleSummoner1 = j-1;
						}
						
					}
					
					for(int j =6; j<11; j++) {
						 Map<String,Object> participant1 = new HashMap<>();
						 Map<String,Object> participant2 = new HashMap<>();
						 participant1 = (Map<String, Object>)participantFrames.get(j+"");
						 participant2 = (Map<String, Object>)participantFrames.get((j-1)+"");
						 if(j==6) {
							 jungleSummoner2 = j;
						 }
						if((participant2 !=null && 
								(int)participant1.get("jungleMinionsKilled")<(int)participant2.get("jungleMinionsKilled"))) {
							jungleSummoner2 = j-1;
						}
						
					}
					participantList.get(jungleSummoner1-1).put("jungle",(int)participantList.get(jungleSummoner1-1).get("jungle")+2);
					participantList.get(jungleSummoner2-1).put("jungle",(int)participantList.get(jungleSummoner2-1).get("jungle")+2);
					
					for(int j =1; j<11; j++) {
						Map<String,Object> participant = (Map<String, Object>)participantFrames.get(j+"");
						Map<String,Object> position = (Map<String,Object>)participant.get("position");
						if(position == null) {
							return null;
						}
						int x = (int)position.get("x");
						int y = (int)position.get("y");
						if(x < 4688 && y < 4688) { //blue
							participantList.get(j-1).put("none",(int)participantList.get(j-1).get("none")+1);
						}else if((10314 < x && x < 15000) && (10314 < y && y < 15000)) { //red
							participantList.get(j-1).put("none",(int)participantList.get(j-1).get("none")+1);
						}else if((9845 < x && x < 15000) && (0 < y && y < 5157)) {//bot
							participantList.get(j-1).put("bot",(int)participantList.get(j-1).get("bot")+1);
						}else if((6564 < x && x < 8437) && (6564 < y && y < 8437)) {//mid
							participantList.get(j-1).put("mid",(int)participantList.get(j-1).get("mid")+1);;
						}else if((0 < x && x < 5157) && (9845 < y && y < 15000)) {//top
							participantList.get(j-1).put("top",(int)participantList.get(j-1).get("top")+1);
						}else {//jungle
							participantList.get(j-1).put("jungle",(int)participantList.get(j-1).get("jungle")+1);
						}
						participantList.get(j-1).put("participantId",participant.get("participantId"));
					}
				}
				List<Map<String,Object>> blue = new ArrayList<>();
				for(int i=0; i<5; i++) {
					blue.add(participantList.get(i));
				}
				sortValue(blue, "jungle");
				MatchGameInfoVO mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)blue.get(0).get("participantId"));
				mgi.setMatchGamePosition("jungle");
				participantPosition.add(mgi);
				blue.remove(0);
				sortValue(blue, "mid");
				mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)blue.get(0).get("participantId"));
				mgi.setMatchGamePosition("mid");
				participantPosition.add(mgi);
				blue.remove(0);
				sortValue(blue, "top");
				mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)blue.get(0).get("participantId"));
				mgi.setMatchGamePosition("top");
				participantPosition.add(mgi);
				blue.remove(0);
				Map<String,Object> timeStmp = new HashMap<>();
				timeStmp = list.get(10);
				Map<String,Object> participantNumBlue =  (Map<String,Object>)timeStmp.get("participantFrames");
				Map<String,Object> minionsKilled1Blue = (Map<String, Object>) participantNumBlue.get(blue.get(0).get("participantNum").toString());
				Map<String,Object> minionsKilled2Blue = (Map<String, Object>) participantNumBlue.get(blue.get(1).get("participantNum").toString());
				if((int)minionsKilled1Blue.get("minionsKilled") < (int)minionsKilled2Blue.get("minionsKilled")) {
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameParticipant((int)blue.get(1).get("participantId"));
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGamePosition("bot_carry");
					participantPosition.add(mgi);
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameParticipant((int)blue.get(0).get("participantId"));
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGamePosition("bot_support");
					participantPosition.add(mgi);
				}else {
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameParticipant((int)blue.get(0).get("participantId"));
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGamePosition("bot_carry");
					participantPosition.add(mgi);
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameParticipant((int)blue.get(1).get("participantId"));
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGamePosition("bot_support");
					participantPosition.add(mgi);
				}
				
				List<Map<String,Object>> red = new ArrayList<>();
				for(int i=5; i<10; i++) {
					red.add(participantList.get(i));
				}
				sortValue(red, "jungle");
				mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)red.get(0).get("participantId"));
				mgi.setMatchGamePosition("jungle");
				participantPosition.add(mgi);
				red.remove(0);
				sortValue(red, "mid");
				mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)red.get(0).get("participantId"));
				mgi.setMatchGamePosition("mid");
				participantPosition.add(mgi);
				red.remove(0);
				sortValue(red, "top");
				mgi = new MatchGameInfoVO();
				mgi.setMatchGameTime((int)m.get("timestamp"));
				mgi.setMatchGameParticipant((int)red.get(0).get("participantId"));
				mgi.setMatchGamePosition("top");
				participantPosition.add(mgi);
				red.remove(0);
				Map<String,Object> participantNumRed =  (Map<String,Object>)timeStmp.get("participantFrames");
				Map<String,Object> minionsKilled1Red = (Map<String, Object>) participantNumRed.get(red.get(0).get("participantNum").toString());
				Map<String,Object> minionsKilled2Red = (Map<String, Object>) participantNumRed.get(red.get(1).get("participantNum").toString());
				if((int)minionsKilled1Red.get("minionsKilled") < (int)minionsKilled2Red.get("minionsKilled")) {
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGameParticipant((int)red.get(1).get("participantId"));
					mgi.setMatchGamePosition("bot_carry");
					participantPosition.add(mgi);
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGameParticipant((int)red.get(0).get("participantId"));
					mgi.setMatchGamePosition("bot_support");
					participantPosition.add(mgi);
				}else {
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGameParticipant((int)red.get(0).get("participantId"));
					mgi.setMatchGamePosition("bot_carry");
					participantPosition.add(mgi);
					mgi = new MatchGameInfoVO();
					mgi.setMatchGameTime((int)m.get("timestamp"));
					mgi.setMatchGameParticipant((int)red.get(1).get("participantId"));
					mgi.setMatchGamePosition("bot_support");
					participantPosition.add(mgi);
				}
				return 	participantPosition;
				
			}
		}
		return null;
	}
	
	public void getMatchData(long matchId) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matches/"+matchId;
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> rMap = new HashMap<>();
		try {
			rMap = om.readValue(rd.getReadData(url), Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(rMap.get("gameMode") == null ||  rMap.get("queueId")==null) {
			return;
		}
		int queueType = (int)rMap.get("queueId");
		String gameMode = rMap.get("gameMode").toString();
		if(!gameMode.equals("CLASSIC") || queueType != 420) {
			log.info("gameMode==>{}",gameMode);
			return;
		}
		List<MatchGameInfoVO> positionList =  setPosition(matchId);
		long matchGameId = (long)rMap.get("gameId");
		
		List<Map<String, Object>> participantIdentities = (List<Map<String, Object>>) rMap.get("participantIdentities");
		List<Map<String,Object>> participants = (List<Map<String,Object>>)rMap.get("participants");
		for(Map<String,Object> participant : participants) {
			Map<String,Object> stats = (Map<String,Object>)participant.get("stats");
			
			MatchItemSlotVO mis = new MatchItemSlotVO();
			int item0 = (int)stats.get("item0");
			mis.setItem0(item0);
			int item1 = (int)stats.get("item1");
			mis.setItem1(item1);
			int item2 = (int)stats.get("item2");
			mis.setItem2(item2);
			int item3 = (int)stats.get("item3");
			mis.setItem3(item3);
			int item4 = (int)stats.get("item4");
			mis.setItem4(item4);
			int item5 = (int)stats.get("item5");
			mis.setItem5(item5);
			int item6 = (int)stats.get("item6");
			mis.setItem6(item6);
			mism.insertMatchItemSlot(mis);
		
			int check;
			for(int i=0; i<6;i++) {
				check = (int) stats.get("perk"+i);
				if(check <0 || check>10000) {
					stats.put("perk"+i,0);
				}
				for(int j=1; j<4; j++) {
					check = (int) stats.get("perk"+i+"Var"+j);
					if(check <0 || check>10000) {
						stats.put("perk"+i+"Var"+j,0);
					}
				}
			}
			
			RunePageVO rp = new RunePageVO();
			int perk0 = (int)stats.get("perk0");
			rp.setPerk0(perk0);
			int perk0Var1 = (int)stats.get("perk0Var1");
			rp.setPerk0Var1(perk0Var1);
			int perk0Var2 = (int)stats.get("perk0Var2");
			rp.setPerk0Var2(perk0Var2);
			int perk0Var3 = (int)stats.get("perk0Var3");
			rp.setPerk0Var3(perk0Var3);
			
			int perk1 = (int)stats.get("perk1");
			rp.setPerk1(perk1);
			int perk1Var1 = (int)stats.get("perk1Var1");
			rp.setPerk1Var1(perk1Var1);
			int perk1Var2 = (int)stats.get("perk1Var2");
			rp.setPerk1Var2(perk1Var2);
			int perk1Var3 = (int)stats.get("perk1Var3");
			rp.setPerk1Var3(perk1Var3);
			
			int perk2 = (int)stats.get("perk2");
			rp.setPerk2(perk2);
			int perk2Var1 = (int)stats.get("perk2Var1");
			rp.setPerk2Var1(perk2Var1);
			int perk2Var2 = (int)stats.get("perk2Var2");
			rp.setPerk2Var2(perk2Var2);
			int perk2Var3 = (int)stats.get("perk2Var3");
			rp.setPerk2Var3(perk2Var3);
			
			int perk3 = (int)stats.get("perk3");
			rp.setPerk3(perk3);
			int perk3Var1 = (int)stats.get("perk3Var1");
			rp.setPerk3Var1(perk3Var1);
			int perk3Var2 = (int)stats.get("perk3Var2");
			rp.setPerk3Var2(perk3Var2);
			int perk3Var3 = (int)stats.get("perk3Var3");
			rp.setPerk3Var3(perk3Var3);
			
			int perk4 = (int)stats.get("perk4");
			rp.setPerk4(perk4);
			int perk4Var1 = (int)stats.get("perk4Var1");
			rp.setPerk4Var1(perk4Var1);
			int perk4Var2 = (int)stats.get("perk4Var2");
			rp.setPerk4Var2(perk4Var2);
			int perk4Var3 = (int)stats.get("perk4Var3");
			rp.setPerk0Var3(perk4Var3);
			
			int perk5 = (int)stats.get("perk5");
			rp.setPerk5(perk5);
			int perk5Var1 = (int)stats.get("perk5Var1");
			rp.setPerk5Var1(perk5Var1);
			int perk5Var2 = (int)stats.get("perk5Var2");
			rp.setPerk5Var2(perk5Var2);
			int perk5Var3 = (int)stats.get("perk5Var3");
			rp.setPerk5Var3(perk5Var3);

			
			int perkPrimaryStyle = (int)stats.get("perkPrimaryStyle");
			rp.setPerkPrimaryStyle(perkPrimaryStyle);
			int perkSubStyle = (int)stats.get("perkSubStyle");
			rp.setPerkSubStyle(perkSubStyle);
			int statPerk0 = (int)stats.get("statPerk0");
			rp.setStatPerk0(statPerk0);
			int statPerk1 = (int)stats.get("statPerk1");
			rp.setStatPerk1(statPerk1);
			int statPerk2 = (int)stats.get("statPerk2");
			rp.setStatPerk2(statPerk2);
			rp.setChampionInfoKey((int)participant.get("championId"));
			rp.setMatchId(matchId);
			rpm.insertRunePage(rp);
			
			Boolean win = (Boolean) stats.get("win");
			String matchGameWin = "lose";
			if(win) {
				matchGameWin = "win";
			}
			MatchGameInfoVO mgi = new MatchGameInfoVO();
			int matchGameParticipant = (int)participant.get("participantId");
			mgi.setMatchGameParticipant(matchGameParticipant);
			int matchGameSpell1 = (int)participant.get("spell1Id");
			mgi.setMatchGameSpell1(matchGameSpell1);
			int matchGameSpell2 = (int)participant.get("spell2Id");
			mgi.setMatchGameSpell2(matchGameSpell2);
			if(matchGameSpell1 == 11 || matchGameSpell2 == 11) {
				mgi.setMatchGamePosition("JUNGLE");
			}
			if(positionList!=null) {
				for(MatchGameInfoVO position : positionList) {
					if(mgi.getMatchGameParticipant() == position.getMatchGameParticipant()) {
						mgi.setMatchGamePosition(position.getMatchGamePosition());
					}
				}
			}else {
				mgi.setMatchGamePosition("none");
			}
			
			for(Map<String, Object> participantIdentity : participantIdentities) {
				Map<String, Object> summonerId= (Map<String, Object>) participantIdentity.get("player");
				if(mgi.getMatchGameParticipant() == (int)participantIdentity.get("participantId")) {
					if(summonerId.get("summonerId") != null) {
						mgi.setSummonerInfoId(summonerId.get("summonerId").toString());
					}else {
						mgi.setSummonerInfoId(null);
					}
					
				}
			}
			
			mgi.setMatchGameId(matchGameId);
			mgi.setMatchGameWin(matchGameWin);
			int matchGameTeam = (int)participant.get("teamId");
			mgi.setMatchGameTeam(matchGameTeam);
			int championInfoKey = (int)participant.get("championId");
			mgi.setChampionInfoKey(championInfoKey);
			int matchGameKills = (int)stats.get("kills");
			mgi.setMatchGameKills(matchGameKills);
			int matchGameDeaths = (int)stats.get("deaths");
			mgi.setMatchGameDeaths(matchGameDeaths);
			int matchGameAssists = (int)stats.get("assists");
			mgi.setMatchGameAssists(matchGameAssists);
			
			
			int matchGameLevel = (int)stats.get("champLevel");
			mgi.setMatchGameLevel(matchGameLevel);
			int totalDamage = (int)stats.get("totalDamageDealtToChampions");
			mgi.setTotalDamage(totalDamage);
			int visionWardsBought = (int)stats.get("visionWardsBoughtInGame");
			mgi.setVisionWardsBought(visionWardsBought);
			int wardsPlaced = (int)stats.get("wardsPlaced");
			mgi.setWardsPlaced(wardsPlaced);
			int wardsKilled = (int)stats.get("wardsKilled");
			mgi.setWardsKilled(wardsKilled);
			int totalMinionsKilled = (int)stats.get("totalMinionsKilled");
			int neutralMinionsKilled = (int)stats.get("neutralMinionsKilled");
			int matchGameCs = totalMinionsKilled+neutralMinionsKilled;	
			mgi.setMatchGameCs(matchGameCs);
			mgi.setMatchGameCreation((long)rMap.get("gameCreation"));
			mgi.setMatchGameTime((int)rMap.get("gameDuration"));
			mgi.setPkMatchItemSlot(mis.getPkMatchItemSlot());
			mgi.setPkRunePage(rp.getPkRunePage());
			mgim.insertMatchGameInfo(mgi);
		}
	}

}
