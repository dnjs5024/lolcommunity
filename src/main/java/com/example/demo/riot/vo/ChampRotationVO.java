package com.example.demo.riot.vo;

import java.util.List;

import lombok.Data;

@Data
public class ChampRotationVO {
	private List<Integer> freeChampionIds;
	private List<Integer> freeChampionIdsForNewPlayers;
	private int maxNewPlayerLevel;
}
