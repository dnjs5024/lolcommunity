package com.example.demo.community.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.demo.community.vo.UserInfoVO;

public interface UserInfoService {
	int joinUserInfo(UserInfoVO user);
	int checkId(UserInfoVO userId);
	int checkNick(UserInfoVO userNick);
	int userLogin(UserInfoVO user, HttpSession hs);
	int userLogOut(HttpSession hs);
    int userUpdate(UserInfoVO user , HttpSession hs);
    int userDelete(UserInfoVO user ,HttpSession hs);
    UserInfoVO selectUserOne(UserInfoVO user);
	
}
