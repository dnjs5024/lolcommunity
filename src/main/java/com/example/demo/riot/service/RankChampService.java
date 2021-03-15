package com.example.demo.riot.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.riot.vo.RankChampVO;


public interface RankChampService {
	public List<Map<String,Object>> selectRankChampList(Map<String,String> tierLine);
}
