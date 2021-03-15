package com.example.demo.riot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.riot.service.SummonerInfoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SummonerController {
	
	@Resource
	private SummonerInfoService sis;
	
	@GetMapping("/games/{summoner}")
	public String doParseGames(@PathVariable String summoner){
		log.info("summoner==>{}",summoner);
		sis.parseGames(summoner);
		return summoner;
	}

}
