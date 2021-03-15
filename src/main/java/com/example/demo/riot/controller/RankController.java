package com.example.demo.riot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.riot.service.RankChampService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class RankController {
	
	@Resource
	private RankChampService rcs;
	
	@PostMapping("/champion/rank")
	public List<Map<String,Object>> test(@RequestBody Map<String,String> tierLine) {
		List<Map<String,Object>> rank = rcs.selectRankChampList(tierLine);
		log.info("rank===>{}", rank);
		return rank;
	}
	
}
