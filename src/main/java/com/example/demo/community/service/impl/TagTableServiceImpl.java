package com.example.demo.community.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.demo.community.mapper.FreeBoardMapper;
import com.example.demo.community.mapper.TagTable;
import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.community.service.TagTableService;
import com.example.demo.community.util.PwdSolt;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TagTableServiceImpl implements TagTableService {

	@Resource
	private TagTable tagTable;
	
	@Resource
	private FreeBoardMapper freeBoardMapper;
	
	@Resource
	private UserInfoMapper userInfoMapper;
	
	
	@Override
	public List<TagTableVO> selectTagList(TagTableVO vo) {
		PageVO page = null;
		if(vo != null) {
			page = vo.getPage();
			if(page == null) {
				page = new PageVO();
				page.setCurrentPageNo(1);
				vo.setPage(page);
			}
		}

		int currentPageNo = vo.getCurrentPageNo();
		int startRow =  ((currentPageNo/10)*10)+1;
		int endRow = startRow+9;
		page.setCurrentPageNo(currentPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		List<TagTableVO> rList =tagTable.selectTagList(vo);
		return rList;
	}
	@Override
	public List<TagTableVO> selectGuideTagList(TagTableVO vo) {
		PageVO page = null;
		if(vo != null) {
			page = vo.getPage();
			if(page == null) {
				page = new PageVO();
				page.setCurrentPageNo(1);
				vo.setPage(page);
			}
		}
		int currentPageNo = vo.getCurrentPageNo();
		int startRow =  ((currentPageNo/10)*10)+1;
		int endRow = startRow+9;
		page.setCurrentPageNo(currentPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		List<TagTableVO> rList =tagTable.selectGuideTagList(vo);
		return rList;
	}

	@Override
	public int insertTag(TagTableVO vo, HttpSession hs) {
		
		if(hs.getAttribute("user") !=null) {
			UserInfoVO user =(UserInfoVO) hs.getAttribute("user");
			String userNick =user.getUserNick();
			int userNum =user.getUserNum();
			vo.setUserNick(userNick);
			vo.setUserNum(userNum);
			userInfoMapper.userAddExperience(user);
			user =userInfoMapper.selectUserExperience(user);
			int userExperience = user.getUserExperience();
			if(userExperience >= 100 ) {
				userInfoMapper.userUpLever(user);
				userInfoMapper.userCleanExperience(user);
			}
			return tagTable.insertTag(vo);
		}
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		vo.setUserNick("익명 "+System.nanoTime());
		return tagTable.insertTag(vo);
	}
	
	@Override
	public int insertGuideTag(TagTableVO vo , HttpSession hs) {
		if(hs.getAttribute("user") !=null) {
			UserInfoVO user =(UserInfoVO) hs.getAttribute("user");
			String userNick =user.getUserNick();
			int userNum =user.getUserNum();
			vo.setUserNick(userNick);
			vo.setUserNum(userNum);
			
			userInfoMapper.userAddExperience(user);
			user =userInfoMapper.selectUserExperience(user);
			int userExperience = user.getUserExperience();
			if(userExperience >= 100 ) {
				userInfoMapper.userUpLever(user);
				userInfoMapper.userCleanExperience(user);
			}
			
			return tagTable.insertGuideTag(vo);
		}
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		vo.setUserNick("익명 "+System.nanoTime());
		return tagTable.insertGuideTag(vo);
	}


	@Override
	public int updateTag(TagTableVO vo, HttpSession hs) {
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		int ghostNum =tagTable.selectGhostPwd(vo);
		
		UserInfoVO user =(UserInfoVO) hs.getAttribute("user");
		int cnt=0;
		if(user != null) {
			cnt =2;
			if(user.getUserNick().equals(vo.getUserNick())) {
				return tagTable.updateTag(vo);
			}
		}else if(ghostNum ==1 ) {
			return tagTable.updateGhostTag(vo);
		}
		
		
		return cnt;
	}
	
	@Override
	public int updateGuideTag(TagTableVO vo , HttpSession hs) {
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		int ghostNum =tagTable.selectGuideGhostPwd(vo);
		
		UserInfoVO user =(UserInfoVO) hs.getAttribute("user");
		int cnt=0;
		if(user != null) {
			cnt =2;
			if(user.getUserNick().equals(vo.getUserNick())) {
				return tagTable.updateGuideTag(vo);
			}
		}else if(ghostNum ==1 ) {
			return tagTable.updateGuideGhostTag(vo);
		}
		
		
		return cnt;
	}

	@Override
	public TagTableVO selectTagOne(TagTableVO vo) {
		return tagTable.selectTagOne(vo);
	}
	
	@Override
	public TagTableVO selectGuideTagOne(TagTableVO vo) {
		// TODO Auto-generated method stub
		return tagTable.selectGuideTagOne(vo);
	}

	@Override
	public int deleteTag(TagTableVO vo ,HttpSession hs) {
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		int ghostNum =tagTable.selectGhostPwd(vo);
		UserInfoVO user = (UserInfoVO) hs.getAttribute("user");
		int cnt=0;
		if(user != null) {
			cnt =2;
			if(user.getUserNick().equals(vo.getUserNick())) {
				return tagTable.deleteTag(vo);
			}
		}else if(ghostNum == 1) {
			return tagTable.deleteTag(vo);
		}
		
		return cnt;
	}
	
	@Override
	public int deleteGuideTag(TagTableVO vo, HttpSession hs) {
		String ghostPwd =vo.getGhostPwd();
		vo.setGhostPwd(PwdSolt.transPwd(ghostPwd));
		int ghostNum =tagTable.selectGuideGhostPwd(vo);
		UserInfoVO user = (UserInfoVO) hs.getAttribute("user");
		int cnt=0;
		if(user != null) {
			cnt =2;
			if(user.getUserNick().equals(vo.getUserNick())) {
				return tagTable.deleteGuideTag(vo);
			}
		}else if(ghostNum == 1) {
			return tagTable.deleteGuideTag(vo);
		}
		
		return cnt;
	}
	

	@Override
	public int selectTagCntUser(UserInfoVO user) {
		int cnt = tagTable.selectTagCntUser(user);
			cnt += tagTable.selectGuideTagCntUser(user);
		return cnt;
	}

	@Override
	public Map<String,Object> selectTagUserList(UserInfoVO user) {
		PageVO page = new PageVO();
		int currentPageNo = user.getCurrentPageNo();
		final int pageRange= 10;
		final int listRange= 10;
		int startPageNo =1;
		int lastPageNo = pageRange;
		if(currentPageNo > (pageRange/2)) {
            startPageNo = currentPageNo - ((lastPageNo/2)-1);
            lastPageNo += (startPageNo-1);
        }	
		int listSize = tagTable.selectTagCntUser(user);//50
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow =currentPageNo*listRange;
		int endPageNo = (listSize-1)/listRange+1;
		
		if(endPageNo<11) {
			lastPageNo=endPageNo;
		}
		
		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		user.setPage(page);
		System.out.println(user);
		List<TagTableVO> rList = tagTable.selectTagUserList(user);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);	
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);
		System.out.println(rMap);
		return rMap;
	}
	@Override
	public Map<String, Object> selectGuideTagUserList(UserInfoVO user) {
		PageVO page = new PageVO();
		int currentPageNo = user.getCurrentPageNo();
		final int pageRange= 10;
		final int listRange= 10;
		int startPageNo =1;
		int lastPageNo = pageRange;
		if(currentPageNo > (pageRange/2)) {
            startPageNo = currentPageNo - ((lastPageNo/2)-1);
            lastPageNo += (startPageNo-1);
        }	
		int listSize = tagTable.selectGuideTagCntUser(user);//50
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow =currentPageNo*listRange;
		int endPageNo = (listSize-1)/listRange+1;
		
		if(endPageNo<11) {
			lastPageNo=endPageNo;
		}
		
		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		user.setPage(page);
		System.out.println(user);
		List<TagTableVO> rList = tagTable.selectGuideTagUserList(user);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);	
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);
		return rMap;
	}
	@Override
	public int checkFreeTagDelete(List<Integer> deleteList) {
		for(int idx : deleteList ) {
			TagTableVO vo = new TagTableVO();
			vo.setTagNum(idx);
			tagTable.checkFreeTagDelete(vo);
		}
		return 0;
	}

	@Override
	public int checkGuideTagDelete(List<Integer> deleteList) {
		for(int idx : deleteList ) {
			TagTableVO vo = new TagTableVO();
			vo.setGuideTagNum(idx);
			tagTable.checkGuideTagDelete(vo);
		}
		return 0;
	}
	
	
	
}
