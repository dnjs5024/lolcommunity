package com.example.demo.community.controller.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.riot.service.RotationChampService;
import com.example.demo.riot.service.SummonerInfoService;

@Controller
public class URIController {

	@Resource
	private SummonerInfoService sis;
	
	@Resource
	private RotationChampService rc;
	
	@GetMapping("/views/**")
	public String goPage(HttpServletRequest req) {
		return req.getRequestURI().substring(1);
	}
	
	@GetMapping("/") 
	public String goHome(Model model) {
		model.addAttribute("champs",rc.getRotationChamp());
		return "views/riot/main";
	}
	
	@GetMapping("/summoner/*")
	public String goSummoner(@RequestParam String summoner, Model model){
		model.addAttribute("user",sis.searchSummonerInfo(summoner));
		return "views/riot/summoner";
	}
	
	@GetMapping("/gamePage/{summoner}/{page}")
	public String getGamePage(@PathVariable String summoner,@PathVariable int page,Model model){
		model.addAttribute("game", sis.getGamePage(summoner,page));
		return "/views/riot/game-info";
	}
	
	
}
