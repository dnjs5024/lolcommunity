package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.community.mapper.TagTable;
import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.community.vo.TagTableVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class Ndibsg2ApplicationTests {
	@Resource
	TagTable tb;
	@Resource
	UserInfoMapper ui;
	
	@Test
	void contextLoads() {
		log.info("list==={}",ui.icons());
	
		
	
	}

}
