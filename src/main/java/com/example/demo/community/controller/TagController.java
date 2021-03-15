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

import com.example.demo.community.service.TagTableService;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TagController {
	
	@Resource
	TagTableService tagService;
	
	@GetMapping("/community/selectTagList")
	public List<TagTableVO> selectTagList(@ModelAttribute TagTableVO vo){
		
		List<TagTableVO> rList =tagService.selectTagList(vo);
		return rList;
	}
	
	@GetMapping("/community/selectTagGuideList")
	public List<TagTableVO> selectTagGuideList(@ModelAttribute TagTableVO vo){
		List<TagTableVO> rList =tagService.selectGuideTagList(vo);
		return rList;
	}
	
	@PostMapping("/community/insertTag")
	public int insertTag(@ModelAttribute TagTableVO vo , HttpSession hs) {
		
		return tagService.insertTag(vo,hs);
	}
	
	@PostMapping("/community/insertGuideTag")
	public int insertGuideTag(@ModelAttribute TagTableVO vo , HttpSession hs) {
		return tagService.insertGuideTag(vo,hs);
	}
	
	
	@PostMapping("/community/updateTag")
	public int updateTag(@ModelAttribute TagTableVO vo , HttpSession hs) {
		int cnt =tagService.updateTag(vo,hs);
		return cnt;
	}
	
	@PostMapping("/community/updateGuideTag")
	public int updateGuideTag(@ModelAttribute TagTableVO vo , HttpSession hs) {
		int cnt =tagService.updateGuideTag(vo, hs);
		return cnt;
	}
	
	
	@PostMapping("/community/selectTagOne")
	public TagTableVO selectTagOne(@ModelAttribute TagTableVO vo) {
		return tagService.selectTagOne(vo);
		
	}
	@PostMapping("/community/selectGuideTagOne")
	public TagTableVO selectGuideTagOne(@ModelAttribute TagTableVO vo) {
	
	    return tagService.selectGuideTagOne(vo);
		
	}
	
	
	@PostMapping("/community/deleteTag")
	public int deleteTag(@ModelAttribute TagTableVO vo ,HttpSession hs) {
		return tagService.deleteTag(vo, hs);		
	}
	
	@PostMapping("/community/deleteGuideTag")
	public int deleteGuideTag(@ModelAttribute TagTableVO vo ,HttpSession hs) {
		return tagService.deleteGuideTag(vo, hs);		
	}
	
	@PostMapping("/community/selectTagCntUser")
	public int selectTagCntUser(@ModelAttribute UserInfoVO user) {
		return tagService.selectTagCntUser(user);
	}
	
	@PostMapping("/community/selectTagUserList")
	public Map<String,Object> selectTagUserList (@ModelAttribute UserInfoVO user){
		Map<String, Object> rMap=tagService.selectTagUserList(user);
		return rMap;
	}
	@PostMapping("/community/selectGuideTagUserList")
	public Map<String,Object> selectGuideTagUserList (@ModelAttribute UserInfoVO user){
		Map<String, Object> rMap=tagService.selectGuideTagUserList(user);
		return rMap;
	}
	@PostMapping("/community/checkFreeTagDelete")
	public int checkFreeTagDelete(@RequestParam(value="deleteList[]") List<Integer> deleteList) {
		return tagService.checkFreeTagDelete(deleteList);
	}
	@PostMapping("/community/checkGuideTagDelete")
	public int checkGuideTagDelete(@RequestParam(value="deleteList[]") List<Integer> deleteList) {
		return tagService.checkGuideTagDelete(deleteList);
	}
		

}
