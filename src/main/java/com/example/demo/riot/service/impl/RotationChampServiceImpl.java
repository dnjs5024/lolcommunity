package com.example.demo.riot.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.riot.mapper.ChampionRotationMapper;
import com.example.demo.riot.service.RotationChampService;
import com.example.demo.riot.vo.ChampRotationVO;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RotationChampServiceImpl implements RotationChampService {

	@Resource
	private ChampionRotationMapper championRotationMapper;

	@Override
	public List<ChampionInfoVO> getRotationChamp() {
		return championRotationMapper.selectChampionRotation();
	}

}
