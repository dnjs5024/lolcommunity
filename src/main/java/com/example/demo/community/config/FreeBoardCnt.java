package com.example.demo.community.config;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.community.mapper.FreeCnt;

import lombok.extern.slf4j.Slf4j;


// 
@Component
@Slf4j
public class FreeBoardCnt {
	@Resource
	FreeCnt cnt;

	@Scheduled(cron = "* * 2 * * *")
	public void test1() {
		
		cnt.deleteFreeCnt();
		
	}
	
}
