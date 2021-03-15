package com.example.demo.riot.service;

import java.util.List;

import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.ChampionSpellVO;

public interface ChampSpellService {
	public List<ChampionSpellVO> champSpells(ChampionInfoVO champion);
}
