package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.ChampionSpellVO;
import com.example.demo.riot.vo.SummonerSpellVO;

@Mapper
public interface SummonerSpellMapper {
	int insertSummonerSpell (SummonerSpellVO summonerSpell);
	SummonerSpellVO selectSummonerSpell (int spellKey);
}
