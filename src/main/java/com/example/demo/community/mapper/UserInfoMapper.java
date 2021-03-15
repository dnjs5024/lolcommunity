package com.example.demo.community.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.example.demo.community.vo.UserInfoVO;

@Mapper
public interface UserInfoMapper {
	int insertUserInfo(UserInfoVO user);
	int checkUserId(UserInfoVO userId);
	int checkUserNick(UserInfoVO userNick);
	int userLogin(UserInfoVO user);
	UserInfoVO userSelectOne(UserInfoVO user);
	List<String> icons(); 
	int userUpdate(UserInfoVO user);
	int userDelete(UserInfoVO user);
	void userAddExperience(UserInfoVO user);
	void userUpLever(UserInfoVO user);
	void userCleanExperience(UserInfoVO user);
	UserInfoVO selectUserExperience(UserInfoVO user);
	UserInfoVO selectUserOne(UserInfoVO user);
	
}
