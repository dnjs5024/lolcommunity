package com.example.demo.riot.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.demo.riot.vo.ChampionInfoVO;

public interface ChampInfoService {
	public ChampionInfoVO selectChampionInfo(ChampionInfoVO ci);
	public List<ChampionInfoVO> selectChampionInfoList(ChampionInfoVO ci,String search);
	public List<ChampionInfoVO> selectChampionInfoPosition(String position);
}
