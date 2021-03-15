package com.example.demo.riot.vo;

import lombok.Data;

@Data
public class RankChampVO {
	private int championInfoKey;
	private int winGame;
	private int kills;
	private int deaths;
	private int assists;
	private int totalGame;
}
