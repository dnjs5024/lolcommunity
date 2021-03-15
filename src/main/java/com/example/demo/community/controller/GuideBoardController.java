package com.example.demo.community.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.community.service.FreeBoardService;
import com.example.demo.community.service.GuideBoardService;
import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GuideBoardController {

	@Resource
	GuideBoardService guideBoardService;
	
	@PostMapping("/community/write1")
	public int writeFreeBoard(@ModelAttribute GuideBoardVO vo , @RequestParam("file") MultipartFile file ) {
		vo.setGuideFile(file);
		int cnt = guideBoardService.writeGuideBoard(vo);
		return cnt;
	}
	
	@GetMapping("/community/selectList1")
	public Map<String, Object> selectList(@ModelAttribute PageVO vo){
		Map<String, Object> rMap=guideBoardService.selectGuideList(vo);
		return rMap;
	}
	@GetMapping("/community/selectSearchList1")
	public Map<String,Object>  selectSearchList(@ModelAttribute GuideBoardVO vo,int currentPageNo){
		Map<String,Object>rMap= guideBoardService.selectSearchList(vo,currentPageNo);
		return rMap;
	}
	@PostMapping("/community/selectOne1")
	public GuideBoardVO selectOne(@ModelAttribute GuideBoardVO vo ,HttpSession hs) {
		GuideBoardVO res = guideBoardService.selectGuideOne(vo,hs);
		return res;
	}
	
	@PostMapping("/community/delete1")
	public int deleteBoard(@ModelAttribute GuideBoardVO vo) {
		return guideBoardService.deleteGuideBoard(vo);
	}
	
	@PostMapping("/community/update1")
	public int updateBoard(@ModelAttribute GuideBoardVO vo ,MultipartFile file) {
		vo.setGuideFile(file);
		return guideBoardService.updateGuideBoard(vo);
	}
	
	@PostMapping("/community/likeTo1")
	public int likeDo (@ModelAttribute LikeVO vo) {
		int res = guideBoardService.insertCheckGuide(vo);
		
		return res;
	}
	
	@GetMapping("/communtiy/selectLike1")
	public int selectLike (@ModelAttribute LikeVO vo) {
		int res =guideBoardService.selectCheck(vo);
		return res;
	}
	@PostMapping("/community/selectUserGuideList")
	public Map<String,Object> selectUserGuideList(@ModelAttribute UserInfoVO user){
		Map<String,Object> rMap =guideBoardService.selectUserGuideList(user);
		 
		return guideBoardService.selectUserGuideList(user);
	}
	
	@GetMapping("/community/selectNewList1")
	public List<GuideBoardVO> selectNewList(){
		 List<GuideBoardVO>rList = guideBoardService.selectNewList();
		return rList;
	}
	@GetMapping("/community/selectBestGuideList")
	public Map<String,Object> selectBestGuideList(int cnt){
		Map<String,Object>rMap = guideBoardService.selectBestGuideList(cnt);
		return rMap;
	}
	@PostMapping("/community/checkGuideBoardDelete")
	public int checkGuideBoardDelete(@RequestParam(value="deleteList[]") List<Integer> deleteList) {
		return guideBoardService.checkGuideBoardDelete(deleteList);
	}
	
	
	
	
	
	
	
}
