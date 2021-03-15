package com.example.demo.community.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.community.mapper.FreeBoardMapper;
import com.example.demo.community.mapper.FreeCnt;
import com.example.demo.community.mapper.LikeTable;
import com.example.demo.community.mapper.TagTable;
import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.community.service.UserInfoService;
import com.example.demo.community.util.PwdSolt;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
	@Resource
	UserInfoMapper userInfoMapper;
	
	@Resource
	FreeBoardMapper freeBoardMapper;
	
	@Resource
	TagTable tagTable;

	@Resource
	LikeTable likeTable;
	@Resource
	FreeCnt freeCnt;
	
	
	
	@Override
	public int joinUserInfo(UserInfoVO user) {
		
		String userPwd = user.getUserPwd();
		String transPwd = PwdSolt.transPwd(userPwd);
		user.setUserPwd(transPwd);
		int cnt =userInfoMapper.insertUserInfo(user);
		return cnt;
	}

	@Override
	public int checkId(UserInfoVO userId) {
		return userInfoMapper.checkUserId(userId);
	}

	@Override
	public int checkNick(UserInfoVO userNick) {
		return userInfoMapper.checkUserNick(userNick);
	}

	@Override
	public int userLogin(UserInfoVO user, HttpSession hs) {
		user.setUserPwd(PwdSolt.transPwd(user.getUserPwd()));
		int cnt = userInfoMapper.userLogin(user);
		
		if(cnt == 1) {
			user =userInfoMapper.userSelectOne(user);
			hs.setAttribute("user", user);
		}
		
		return cnt;
	}

	@Override
	public int userLogOut(HttpSession hs) {
		hs.invalidate();
		return 1;
	}

	@Override
	public int userUpdate(UserInfoVO userInfo , HttpSession hs) {
		String transPwd =PwdSolt.transPwd(userInfo.getUserPwd());
		userInfo.setUserPwd(transPwd);
		int cnt =userInfoMapper.userUpdate(userInfo);
		UserInfoVO user =userInfoMapper.userSelectOne(userInfo);
		hs.setAttribute("user", user);
		tagTable.updateTagNick(userInfo);
		return cnt;
	}

	@Override
	public int userDelete(UserInfoVO user ,HttpSession hs) {
		LikeVO likeVO = new LikeVO();
		hs.invalidate();
		freeCnt.deleteFreeCnt();
		likeTable.deleteAll(likeVO);
		tagTable.deleteTagUser(user);
		freeBoardMapper.deleteFreeBoardUser(user);
		int cnt =userInfoMapper.userDelete(user);
		return cnt;
	}

	@Override
	public UserInfoVO selectUserOne(UserInfoVO user) {
		
		return userInfoMapper.selectUserOne(user);
	}

	

}
