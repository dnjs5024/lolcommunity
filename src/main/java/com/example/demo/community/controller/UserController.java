package com.example.demo.community.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.community.config.SessionConfig;
import com.example.demo.community.service.UserInfoService;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	@Resource
	UserInfoService userService;
	
	@PostMapping("/user/join")
	public int userJoin(@ModelAttribute UserInfoVO user) {
		return userService.joinUserInfo(user);
	}
	
	@GetMapping("/user/check_id")
	public  int idCheck(@ModelAttribute UserInfoVO userId) {
		int cnt = userService.checkId(userId);
		return cnt;
	}
	@GetMapping("/user/check_nick")
	public  int nickCheck(@ModelAttribute UserInfoVO userNick) {
		int cnt = userService.checkNick(userNick);
		return cnt ;
	}
	
	@PostMapping("/user/login")
	public int userLogin(@ModelAttribute UserInfoVO user,HttpSession hs) {	
		int cnt = userService.userLogin(user, hs);
		return cnt;
	}
	
	@GetMapping("/user/logOut")
	public int userLogOut(HttpSession hs) {
		userService.userLogOut(hs);
		return 1;
	}
	
	@PostMapping("/user/update")
	public int userUpdate(@ModelAttribute UserInfoVO user , HttpSession hs) {
		
		return userService.userUpdate(user,hs);
	}
	
	@PostMapping("/user/delete")
	public int userDelete (@ModelAttribute UserInfoVO user ,HttpSession hs) {
		
		return userService.userDelete(user ,hs);
	}
	
	@PostMapping("/user/selectUserOne")
	public UserInfoVO selectUserOne (@ModelAttribute UserInfoVO user) {
		return userService.selectUserOne(user);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
