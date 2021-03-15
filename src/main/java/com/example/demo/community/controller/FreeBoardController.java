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
import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FreeBoardController {

	@Resource
	FreeBoardService freeBoardService;
	
	@PostMapping("/community/write")
	public int writeFreeBoard(@ModelAttribute FreeBoardVO vo , @RequestParam("file") MultipartFile file ) {
		vo.setFreeFile(file);
		int cnt = freeBoardService.writeFreeBoard(vo);
		return cnt;
	}
	
	@GetMapping("/community/selectList")
	public Map<String, Object> selectList(@ModelAttribute PageVO vo){
		log.info("msg=={}","테스트입니당");
		Map<String, Object> rMap=freeBoardService.selectFreeList(vo);
		return rMap;
	}
	@GetMapping("/community/selectSearchList")
	public Map<String,Object>  selectSearchList(@ModelAttribute FreeBoardVO vo,int currentPageNo){
		Map<String,Object>rMap= freeBoardService.selectSearchList(vo,currentPageNo);
		return rMap;
	}
	@PostMapping("/community/selectOne")
	public FreeBoardVO selectOne(@ModelAttribute FreeBoardVO vo ,HttpSession hs) {
		FreeBoardVO res = freeBoardService.selectFreeOne(vo,hs);
		
		return res;
	}
	
	@PostMapping("/community/delete")
	public int deleteBoard(@ModelAttribute FreeBoardVO vo) {
		
		return freeBoardService.deleteFreeBoard(vo);
	}
	
	@PostMapping("/community/update")
	public int updateBoard(@ModelAttribute FreeBoardVO vo ,MultipartFile file) {
		vo.setFreeFile(file);
		return freeBoardService.updateFreeBoard(vo);
	}
	
	@PostMapping("/community/likeTo")
	public int likeDo (@ModelAttribute LikeVO vo) {
		
		int res = freeBoardService.insertCheck(vo);
		return res;
	}
	
	@GetMapping("/communtiy/selectLike")
	public int selectLike (@ModelAttribute LikeVO vo) {
		int res =freeBoardService.selectCheck(vo);
	
		return res;
	}
	
	@PostMapping("/community/selectFreeBoardUser")
	public int selectFreeBoardUser(@ModelAttribute UserInfoVO user) {
		return freeBoardService.selectFreeNumUser(user);
	}
	
	@PostMapping("/community/selectUserFreeList")
	public Map<String,Object> selectUserFreeList(@ModelAttribute UserInfoVO user){
		return freeBoardService.selectUserFreeList(user);
	}
	
	@GetMapping("/community/selectNewList")
	public List<FreeBoardVO> selectNewList(){
		 List<FreeBoardVO>rList = freeBoardService.selectNewList();
		return rList;
	}
	@GetMapping("/community/selectBestFreeList")
	public Map<String,Object> selectBestFreeList(int cnt){
		Map<String,Object>rMap = freeBoardService.selectBestFreeList(cnt);
		return rMap;
	}
	@PostMapping("/community/checkFreeBoardDelete")
	public int checkFreeBoardDelete(@RequestParam(value="deleteList[]") List<Integer> deleteList) {
		return freeBoardService.checkFreeBoardDelete(deleteList);
	}
	
}
