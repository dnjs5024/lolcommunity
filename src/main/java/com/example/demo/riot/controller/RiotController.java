package com.example.demo.riot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.riot.service.ChampInfoService;
import com.example.demo.riot.service.ChampSpellService;
import com.example.demo.riot.service.RotationChampService;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.example.demo.riot.vo.ChampionSpellVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RiotController {
	
	@Autowired
	ChampSpellService cs;
	@Autowired
	ChampInfoService ci;
	
	@GetMapping("/champion/{champion}")
	public String goChamion(@PathVariable("champion") String champion, Model model) {
		ChampionInfoVO ci = new ChampionInfoVO();
		ci.setChampionInfoId(champion);
		model.addAttribute("champion",this.ci.selectChampionInfo(ci));
		return "views/riot/champion";
	}
	
	@GetMapping("/champion-list")
	public String goChamionList() {
		return "views/riot/champion-list";
	}
	
		
}
