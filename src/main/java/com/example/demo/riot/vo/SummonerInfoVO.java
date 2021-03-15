package com.example.demo.riot.vo;

import com.example.demo.community.vo.PageVO;

import lombok.Data;

@Data
public class SummonerInfoVO {
	private String summonerInfoTier;
	private String summonerInfoRank;
	private String summonerInfoId;
	private String summonerInfoName;
	private long summonerInfoMod;
	private int summonerInfoLevel;
	private int summonerInfoPoint;
	private int summonerInfoIcon;
	private int summonerInfoWins;
	private int summonerInfoLosses;
	private String summonerInfoAcid;
	private PageVO page;
}
