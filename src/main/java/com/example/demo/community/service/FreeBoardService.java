package com.example.demo.community.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

public interface FreeBoardService {
	int writeFreeBoard(FreeBoardVO vo);
	Map<String,Object> selectFreeList(PageVO vo);
	FreeBoardVO selectFreeOne(FreeBoardVO vo, HttpSession hs);
	int deleteFreeBoard(FreeBoardVO vo);
	int updateFreeBoard(FreeBoardVO vo);
	Map<String,Object> selectSearchList(FreeBoardVO vo,int currentPageNo);
	int insertCheck(LikeVO vo);
	int selectCheck(LikeVO vo);
	int selectFreeNumUser(UserInfoVO user);
	Map<String, Object>selectUserFreeList(UserInfoVO user);
	List<FreeBoardVO>selectNewList();
	int checkFreeBoardDelete(List<Integer> deleteList);
	Map<String,Object>selectBestFreeList(int cnt);
	

	
}
