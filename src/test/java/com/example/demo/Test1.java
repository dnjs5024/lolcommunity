package com.example.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.riot.component.RiotData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class Test1 {
	
	@Resource
	RiotData rd;
	
	@Test
	public void test() {
		String url = "http://static.developer.riotgames.com/docs/lol/queues.json";
		ObjectMapper om = new ObjectMapper();
		List<Map<String,Object>> queueType = new ArrayList<>();
		try {
			 queueType = om.readValue(rd.getStaticReadData(url), List.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for(Map<String,Object> type : queueType) {
			String note = "";
			if(type.get("notes") != null) {
				note = type.get("notes").toString();
				if(note.indexOf("eprecated") != -1) {
					continue;
				}
			}
			
			log.info("type==>{}",type);
		}
		
		
	}
		
	
		

}
