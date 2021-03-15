package com.example.demo.riot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.mapper.ChampionRotationMapper;
import com.example.demo.riot.service.ChampInfoService;
import com.example.demo.riot.vo.ChampionInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChampInfoServiceImpl implements ChampInfoService {

	@Resource
	private ChampionInfoMapper cim;
	
	@Resource
	private ChampionRotationMapper crm;
	
	@Override
	public ChampionInfoVO selectChampionInfo(ChampionInfoVO ci) {
		return cim.selectChampionInfo(ci);
	}

	@Override
	public List<ChampionInfoVO> selectChampionInfoList(ChampionInfoVO ci, String search) {
		List<ChampionInfoVO> list = cim.selectChampionInfoList(ci);
		return list;
	}

	@Override
	public List<ChampionInfoVO> selectChampionInfoPosition(String position) {
		if("rotation".equals(position)) {
			return crm.selectChampionRotation();
		}
		return cim.selectChampionInfoPosition(position);
	}

}
