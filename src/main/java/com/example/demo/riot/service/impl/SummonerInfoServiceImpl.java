package com.example.demo.riot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.vo.PageVO;
import com.example.demo.riot.component.ParseMatchGame;
import com.example.demo.riot.component.RiotData;
import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.mapper.MatchGameInfoMapper;
import com.example.demo.riot.mapper.MatchItemSlotMapper;
import com.example.demo.riot.mapper.RuneInfoMapper;
import com.example.demo.riot.mapper.RunePageMapper;
import com.example.demo.riot.mapper.SummonerInfoMapper;
import com.example.demo.riot.mapper.SummonerSpellMapper;
import com.example.demo.riot.service.SummonerInfoService;
import com.example.demo.riot.utill.GameTime;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.MatchGameInfoVO;
import com.example.demo.riot.vo.MatchItemSlotVO;
import com.example.demo.riot.vo.RunePageVO;
import com.example.demo.riot.vo.SummonerInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SummonerInfoServiceImpl implements SummonerInfoService {

	@Resource
	private SummonerInfoMapper sim;
	@Resource
	private MatchGameInfoMapper mim;
	
	@Resource
	private ChampionInfoMapper cim;
	@Resource
	private MatchGameInfoMapper mgim;
	@Resource
	private SummonerSpellMapper ssm;
	@Resource
	private RunePageMapper rpm; // matchGame의 pk rune
	@Resource
	private RuneInfoMapper rim;
	@Resource
	private MatchItemSlotMapper mism;
	
	
	@Autowired
	RiotData rd;
	
	@Autowired
	ParseMatchGame pmg;
	
	public List<Map<String,Object>> matchGameParse(List<MatchGameInfoVO> matchGames,String summonerId) {
		List<Map<String,Object>> rMatchGame = new ArrayList<>();
		for(MatchGameInfoVO matchGame : matchGames) {
			Map<String,Object> summonerInfo = new HashMap<>();
			if(summonerId.equals(matchGame.getSummonerInfoId())) {
				summonerInfo.put("mMatchGame",matchGame);
				MatchItemSlotVO mis = new MatchItemSlotVO();
				mis.setPkMatchItemSlot(matchGame.getPkMatchItemSlot());
				mis= mism.selectMatchItemSlotByKey(mis);
				RunePageVO rpv = new RunePageVO();
				rpv.setPkRunePage(matchGame.getPkRunePage());
				rpv = rpm.selectRunePageByPage(rpv);
				
				summonerInfo.put("mRune1",rim.selectRuneInfo(rpv.getPerk0()));
				summonerInfo.put("mRune2",rim.selectRuneInfo(rpv.getPerkSubStyle()));
				summonerInfo.put("mSpell1",ssm.selectSummonerSpell(matchGame.getMatchGameSpell1()));
				summonerInfo.put("mSpell2",ssm.selectSummonerSpell(matchGame.getMatchGameSpell2()));
				
				GameTime pg = new GameTime();
				summonerInfo.put("betweenTime",pg.betweenTime(matchGame.getMatchGameCreation()));
				summonerInfo.put("playTime",pg.playTime(matchGame.getMatchGameTime()));
				ChampionInfoVO champ = new ChampionInfoVO();
				int championInfoKey = matchGame.getChampionInfoKey();
				champ.setChampionInfoKey(championInfoKey);
				champ = cim.selectChampionInfo(champ);
				
				
				int kills = matchGame.getMatchGameKills();
				double deaths = matchGame.getMatchGameDeaths();
				int assists = matchGame.getMatchGameAssists();
				if(deaths == 0) {
					deaths = 0.9;
				}
				double kda = (kills+assists)*1.0/deaths;
				kda = Math.round(kda*100)/100.0;
				summonerInfo.put("kda",kda);
				
				summonerInfo.put("mMatchGame",matchGame);
				summonerInfo.put("mChamp",champ);
				summonerInfo.put("mItems",mis);
				rMatchGame.add(summonerInfo);
			}else {
				summonerInfo.put("matchGame",matchGame);
				MatchItemSlotVO mis = new MatchItemSlotVO();
				mis.setPkMatchItemSlot(matchGame.getPkMatchItemSlot());
				mis= mism.selectMatchItemSlotByKey(mis);
				
				RunePageVO rpv = new RunePageVO();
				rpv.setPkRunePage(matchGame.getPkRunePage());
				rpv = rpm.selectRunePageByPage(rpv);
				summonerInfo.put("rune1",rim.selectRuneInfo(rpv.getPerk0()));
				summonerInfo.put("rune2",rim.selectRuneInfo(rpv.getPerkSubStyle()));
				summonerInfo.put("spell1",ssm.selectSummonerSpell(matchGame.getMatchGameSpell1()));
				summonerInfo.put("spell2",ssm.selectSummonerSpell(matchGame.getMatchGameSpell2()));
				
				ChampionInfoVO champ = new ChampionInfoVO();
				int championInfoKey = matchGame.getChampionInfoKey();
				champ.setChampionInfoKey(championInfoKey);
				champ = cim.selectChampionInfo(champ);
				
				int kills = matchGame.getMatchGameKills();
				double deaths = matchGame.getMatchGameDeaths();
				int assists = matchGame.getMatchGameAssists();
				if(deaths == 0) {
					deaths = 0.9;
				}
				double kda = (kills+assists)*1.0/deaths;
				kda = Math.round(kda*100)/100.0;
				summonerInfo.put("kda",kda);
				
				summonerInfo.put("matchGame",matchGame);
				summonerInfo.put("champ",champ);
				summonerInfo.put("items",mis);
				rMatchGame.add(summonerInfo);
			}
		}
		return rMatchGame;
	}
	
	public SummonerInfoVO getSummoner(String summoner){
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+summoner;
		ObjectMapper om = new ObjectMapper();
		SummonerInfoVO siv = new SummonerInfoVO();
		try {
			Map<String,Object> summonerInfo =  om.readValue(rd.getReadData(url), Map.class);
		
			if(summonerInfo.get("status") != null) {//오류가 발생하면
				return null;
			}
			siv.setSummonerInfoId(summonerInfo.get("id").toString());
			siv.setSummonerInfoAcid(summonerInfo.get("puuid").toString());
			siv.setSummonerInfoName(summoner);
			if((int)summonerInfo.get("profileIconId")<0) {
				siv.setSummonerInfoIcon(0);
			}else {
				siv.setSummonerInfoIcon((int)summonerInfo.get("profileIconId"));	
			}
			siv.setSummonerInfoLevel((int)summonerInfo.get("summonerLevel"));
			url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+siv.getSummonerInfoId();
			List<Map<String,Object>> summonerInfo2 =  om.readValue(rd.getReadData(url), List.class);
			if(summonerInfo2.size() == 0) {
				return siv;
			}
			for(Map<String,Object> sum : summonerInfo2) {
				if("RANKED_SOLO_5x5".equals(sum.get("queueType"))) { // 솔로 랭크만 체크 
					siv.setSummonerInfoTier(sum.get("tier").toString());
					siv.setSummonerInfoPoint((int)sum.get("leaguePoints"));
					siv.setSummonerInfoLosses((int)sum.get("losses"));
					siv.setSummonerInfoRank(sum.get("rank").toString());
					siv.setSummonerInfoWins((int)sum.get("wins"));
					return siv;
				}
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<List<Map<String,Object>>> getGamePage(String userName,int pageNo){
		userName = userName.replace(" ", "");
		SummonerInfoVO summoner = sim.selectSummonerInfo(userName);
		PageVO page = new PageVO();
		page.setStartPageNo(1);
		page.setLastPageNo(10);
		summoner.setPage(page);
		List<MatchGameInfoVO> games = mgim.selectMatchGameListById(summoner);
		
		List<List<Map<String,Object>>> gameLists = new ArrayList<>();
		for(MatchGameInfoVO game : games) { //10개씩 게임 데이터 분석
			List<Map<String,Object>> gameList = new ArrayList<>();
			String matchGameId = game.getMatchGameId();
			List<MatchGameInfoVO> matchGame = mgim.selectMatchGameId(matchGameId);
			gameList = matchGameParse(matchGame, summoner.getSummonerInfoId());
			gameLists.add(gameList);
		}
		return gameLists;
	}
	
	
	public void parseGames(String summoner) {
		summoner = summoner.replace(" ", "");
		SummonerInfoVO summonerInfo = sim.selectSummonerInfo(summoner);
		if(summonerInfo==null) {
			return;
		}else {
			int cnt = mgim.totalMatchGameByName(summoner);
			long timeValue = summonerInfo.getSummonerInfoMod();
			Date date = new Date();
			long today = date.getTime();
			
			long betweenTime = (long) Math.floor( (today - timeValue) / 1000/ 60);
			if(betweenTime<6 && cnt !=0) {
				
				return;
			}else{
				summonerInfo.setSummonerInfoMod(today);
				sim.updateSummonerInfo(summonerInfo);
			}
			
		}
		
		String url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+summonerInfo.getSummonerInfoAcid()+
				"/ids";
		ObjectMapper om = new ObjectMapper();
		List<List<MatchGameInfoVO>> lMatchGame = new ArrayList<>();
		try {
			List<String> list = om.readValue(rd.getReadData(url), List.class);

			if(list==null) {
				return;
			}
			for(String match : list) {
				List<MatchGameInfoVO> matchGame = new ArrayList<>();
				String matchId = match; // 
				matchGame = mim.selectMatchGameId(matchId);
				if(matchGame.size() != 0) {
					lMatchGame.add(matchGame);
					continue;
				}else {
					
					log.info("matchGame 없음 ==>{}",matchGame);
					Thread.sleep(2400);
					pmg.getMatchData(matchId);
					lMatchGame.add(mim.selectMatchGameId(matchId));
				}

			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SummonerInfoVO searchSummonerInfo(String summoner) {
		
		if(summoner == null || summoner.trim() == "") {// 빈문자 or null 값 벨리데이션
			return null;
		}
		summoner = summoner.replace(" ", "");
		Date date = new Date();
		SummonerInfoVO summonerInfo = sim.selectSummonerInfo(summoner);
		long nowTime = date.getTime();
		if(summonerInfo == null) {
			summonerInfo = getSummoner(summoner);
			if(summonerInfo != null) {
				summonerInfo.setSummonerInfoMod(nowTime);
				 sim.insertSummonerInfo(summonerInfo);
				
			}else {
				return null;
			}
		}else {
			long m = summonerInfo.getSummonerInfoMod();
			if(m==0) {
				summonerInfo = getSummoner(summoner);
				summonerInfo.setSummonerInfoMod(nowTime);
				sim.updateSummonerInfo(summonerInfo);
			}
		}
		return sim.selectSummonerInfo(summoner);
	}
}
