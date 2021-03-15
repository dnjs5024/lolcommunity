package com.example.demo.community.service;

import java.util.Map;

public interface LoLNewsService {
	Map<String,Object>LoLNewsList(int cnt);
	Map<String,Object>LoLNewsImg(int cnt);
}
