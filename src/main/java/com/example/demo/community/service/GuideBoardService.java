package com.example.demo.community.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

public interface GuideBoardService {
	int writeGuideBoard(GuideBoardVO vo);
	Map<String,Object> selectGuideList(PageVO vo);
	GuideBoardVO selectGuideOne(GuideBoardVO vo, HttpSession hs);
	int deleteGuideBoard(GuideBoardVO vo);
	int updateGuideBoard(GuideBoardVO vo);
	Map<String,Object> selectSearchList(GuideBoardVO vo,int currentPageNo);
	int insertCheckGuide(LikeVO vo);
	int selectCheck(LikeVO vo);
    Map<String, Object> selectUserGuideList(UserInfoVO user);
    List<GuideBoardVO>selectNewList();
	int checkGuideBoardDelete(List<Integer> deleteList);
	Map<String,Object>selectBestGuideList(int cnt);
}
