package com.example.demo.community.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

public interface TagTableService {
	List<TagTableVO> selectTagList(TagTableVO vo);
	int insertTag(TagTableVO vo,HttpSession hs);
	int updateTag(TagTableVO vo,HttpSession hs);
	TagTableVO selectTagOne(TagTableVO vo);
	int deleteTag(TagTableVO vo ,HttpSession hs);
	int selectTagCntUser(UserInfoVO user);
	Map<String,Object> selectTagUserList(UserInfoVO user);
	Map<String,Object> selectGuideTagUserList(UserInfoVO user);
	int insertGuideTag(TagTableVO vo ,HttpSession hs);
	List<TagTableVO>selectGuideTagList(TagTableVO vo);
	TagTableVO selectGuideTagOne(TagTableVO vo);
	int updateGuideTag(TagTableVO vo ,HttpSession hs);
	int deleteGuideTag(TagTableVO vo ,HttpSession hs);
	int checkFreeTagDelete(List<Integer> deleteList);
	int checkGuideTagDelete(List<Integer> deleteList);
}
