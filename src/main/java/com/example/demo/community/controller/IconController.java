package com.example.demo.community.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.riot.mapper.ChampionInfoMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IconController {
	
	@Resource
	ChampionInfoMapper um;
	@Resource
	UserInfoMapper userInfoMapper;
	
	@GetMapping("/views/user/join")
	public void gogo(Model model) {
		model.addAttribute("icon",userInfoMapper.icons());
	}
	
	@GetMapping("/views/user/update")
	public void gogo1(Model model) {
		model.addAttribute("icon",userInfoMapper.icons());
	}

}
