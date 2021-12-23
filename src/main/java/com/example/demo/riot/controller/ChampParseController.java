package com.example.demo.riot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.riot.service.ChampInfoService;
import com.example.demo.riot.service.ChampSpellService;
import com.example.demo.riot.service.RankChampService;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.ChampionSpellVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChampParseController {
	
	@Autowired
	private ChampSpellService cs;
	@Autowired
	private ChampInfoService ci;
	@Autowired
	private RankChampService rcs;


	@GetMapping("/champList")
	public List<ChampionInfoVO> doChampionList(String search) {
		log.info("{}",search);
		return ci.selectChampionInfoList(null, search);
	}
	@GetMapping("/champ/position")
	public List<ChampionInfoVO> doPositionList(String position) {
		return ci.selectChampionInfoPosition(position);
	}
	
	@PostMapping("/spell")
	public List<ChampionSpellVO> doChampionSpells(ChampionInfoVO civ) {
		log.info("{}",civ);
		List<ChampionSpellVO>cs1 = cs.champSpells(civ);
		log.info("{}",cs1);
		return cs.champSpells(civ);
	}
	
}
