package com.example.demo.riot.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.riot.component.RiotData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class VersionCheckSchedule {
	
	@Autowired
	private RiotData rd;
	
	@Scheduled(fixedRateString = "1000000", initialDelay = 3000)
	void scheduleTest(){
		String vUrl = "https://ddragon.leagueoflegends.com/api/versions.json";
		String str = rd.getReadData(vUrl);
		ObjectMapper om = new ObjectMapper();
		try {
			List<String> versions = om.readValue(str, List.class);
			log.info("new-version=>{}",versions.get(0));
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
}
