package com.example.demo.community.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.community.service.LoLNewsService;

@RestController
public class NewsController {
	
	@Resource
	LoLNewsService LoLservice;
	@PostMapping("/community/lolnews")
	public Map<String,Object> LoLNewsList(int cnt){	
		return LoLservice.LoLNewsList(cnt);
	}
	@PostMapping("/community/lolnewsimg")
	public Map<String,Object> LoLNewsImg(int cnt){	
		return LoLservice.LoLNewsImg(cnt);
	}
	
}
