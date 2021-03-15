package com.example.demo.riot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.mapper.ChampionSpellMapper;
import com.example.demo.riot.service.ChampSpellService;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.ChampionSpellVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChampSpellServiceImpl implements ChampSpellService {
	
	@Resource
	private ChampionSpellMapper csMapper;
	@Resource
	private ChampionInfoMapper ciMapper;

	@Override
	public List<ChampionSpellVO> champSpells(ChampionInfoVO champion) {
		ChampionInfoVO ci = ciMapper.selectChampionInfo(champion);
		return csMapper.selectChampionSpellList(ci);
	}

}
