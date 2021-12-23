package com.example.demo.riot.vo;

import lombok.Data;

@Data
public class MatchGameInfoVO { 
	private String matchGameId;
	private int matchGameTeam;
	private String matchGameWin;
	private int championInfoKey;
	private int matchGameSpell1;
	private int matchGameSpell2;
	private String matchGamePosition;
	private String summonerInfoId;
	private int matchGameParticipant;
	private int matchGameKills;
	private int matchGameDeaths;
	private int matchGameAssists;
	private int pkRunePage;
	private int pkMatchItemSlot;
	private int totalDamage;
	private int visionWardsBought;
	private int wardsPlaced;
	private int wardsKilled;
	private int matchGameCs;
	private long matchGameCreation;
	private int matchGameTime;
	private int matchGameLevel;
}
