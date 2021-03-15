package com.example.demo.riot.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.riot.component.RiotData;
import com.example.demo.riot.mapper.ChampionRotationMapper;
import com.example.demo.riot.vo.ChampRotationVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RotationSchedule {

	@Autowired
	private RiotData riotData;

	@Resource
	private ChampionRotationMapper championRotationMapper;

	public int updateRotaion(List<Integer> rotaionList) {
		championRotationMapper.deleteChampionRotation();
		int result = 0;
		for (int championInfoKey : rotaionList) {
			result += championRotationMapper.insertChampionRotation(championInfoKey);
		}
		return result;
	}


	@Scheduled(cron = "0 0 0/1 * * 2") //cron = "0 0 1 * 2 *" 화요일 1시간씩 체크
	public void checkRotation() {
		String urlStr = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations";
		int RotationCnt = 15;
		try {
			ObjectMapper om = new ObjectMapper();
			ChampRotationVO rc = om.readValue(riotData.getReadData(urlStr), ChampRotationVO.class);
			// 챔피언 로테이션 리스트
			List<Integer> rotationList = rc.getFreeChampionIds();
			log.info("{}", rotationList);
			log.info("매일 1분마다 실행");
			// 챔피언 로테이션 개수 : 15
			int result = 0;
			if (rotationList.size() == RotationCnt) {
				result = updateRotaion(rotationList);
				if (result == RotationCnt) {
					log.info("로테이션 변경완료");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
