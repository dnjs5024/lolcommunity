package com.example.demo.community.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.community.mapper.GuideBoardMapper;
import com.example.demo.community.mapper.GuideCnt;
import com.example.demo.community.mapper.LikeTable;
import com.example.demo.community.mapper.TagTable;
import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.community.service.GuideBoardService;
import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.FreeCntVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.GuideCntVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GuideBoardServiceImpl implements GuideBoardService {
	String filePath = "C:\\Users\\user\\git\\ndibsg2\\src\\main\\webapp\\resources\\guideBoardimg\\";
	@Resource
	GuideBoardMapper guideBoard;

	@Resource
	GuideCnt guideCnt;
	
	@Resource
	LikeTable likeTable;
	
	@Resource
	TagTable tagTable;
	
	@Resource
	UserInfoMapper userInfoMapper;
	
	@Override
	public int writeGuideBoard(GuideBoardVO vo) {
		UserInfoVO user = new UserInfoVO();
		if (vo.getGuideFile().getSize() != 0) {
			MultipartFile file = vo.getGuideFile();
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String transName = System.nanoTime() + extName;
			vo.setGuideFilepath(transName);
			File f = new File(filePath + transName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			user.setUserNum(vo.getUserNum());
			userInfoMapper.userAddExperience(user);
			user =userInfoMapper.selectUserExperience(user);
			
			int userExperience = user.getUserExperience();
			if(userExperience == 100 || userExperience == 250 || userExperience == 500) {
				userInfoMapper.userUpLever(user);
			}
			
			return guideBoard.writeGuideBoard(vo);
		}
		user.setUserNum(vo.getUserNum());
		userInfoMapper.userAddExperience(user);
		user =userInfoMapper.selectUserExperience(user);
		int userExperience = user.getUserExperience();
		if(userExperience == 100 || userExperience == 250 || userExperience == 500) {
			userInfoMapper.userUpLever(user);
		}
		int cnt = guideBoard.writeGuideBoard(vo);
		return cnt;
	}

	@Override
	public Map<String, Object> selectGuideList(PageVO page) {
		int currentPageNo = page.getCurrentPageNo();
		final int pageRange= 10;
		final int listRange= 20;
		int startPageNo =1;
		int lastPageNo = pageRange;
		if(currentPageNo > (pageRange/2)) {
            
            startPageNo = currentPageNo - ((lastPageNo/2)-1);
        
            lastPageNo += (startPageNo-1);
        }	
		int listSize = guideBoard.selectGuideNum();
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow =currentPageNo*listRange;
		int endPageNo = (listSize-1)/listRange+1;
		if(endPageNo<11) {
			lastPageNo=endPageNo;
           ;
		}
	
		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		GuideBoardVO vo = new GuideBoardVO();	
		vo.setPage(page);
		List<GuideBoardVO> rList = guideBoard.selectGuideList(vo);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);	
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);
		return rMap;
	}

	@Override
	public GuideBoardVO selectGuideOne(GuideBoardVO vo, HttpSession hs) {
		GuideCntVO guideCntVO = new GuideCntVO();
		guideCntVO.setSessionKey(hs.getId());
		guideCntVO.setGuideBoard(vo);
		int cnt = guideCnt.selectAddCnt(guideCntVO);
		if (cnt != 1) {
			int up = guideBoard.guideBoardCnt(vo);
			guideCnt.guideAddCnt(guideCntVO);

		}

		GuideBoardVO res = guideBoard.selectGuideOn(vo);
		return res;
	}

	@Override
	public int deleteGuideBoard(GuideBoardVO vo) {
//		File f = new File(filePath + vo.getGuideFilepath());
//		if (f.exists()) {
//			f.delete();
//		}
//		guideCnt.deleteGuideCnt();
//		likeTable.deleteAll();
//		int cnt =tagTable.deleteAll(vo);
//		log.info("cnt=={}",cnt);
		return guideBoard.deleteGuideBoard(vo);
	}

	@Override
	public int updateGuideBoard(GuideBoardVO vo) {
		File f = new File(filePath + vo.getGuideFilepath());
		if (f.exists()) {
			f.delete();
		}
		if(vo.getGuideFile().getSize() != 0) {
			MultipartFile file = vo.getGuideFile();
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String transName = System.nanoTime() + extName;
			vo.setGuideFilepath(transName);
			File fPath = new File(filePath + transName);
			try {
				file.transferTo(fPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return guideBoard.updateGuideBoard(vo);
		}
		return guideBoard.updateGuideBoard(vo);
	}
	@Override
	public Map<String, Object> selectSearchList(GuideBoardVO vo,int currentPageNo) {
		PageVO page = new PageVO();
		page.setCurrentPageNo(currentPageNo); 
		final int pageRange= 10;
		final int listRange= 20;
		int startPageNo =1;
		int lastPageNo = pageRange;
		if(currentPageNo > (pageRange/2)) {
            
            startPageNo = currentPageNo - ((lastPageNo/2)-1);
        
            lastPageNo += (startPageNo-1);
        }	
		int listSize = guideBoard.selectSearchSize(vo);
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow =currentPageNo*listRange;
		int endPageNo = (listSize-1)/listRange+1;
		if(endPageNo<11) {
			lastPageNo=endPageNo;
           ;
		}
		
		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		vo.setPage(page);
		List<GuideBoardVO> rList = guideBoard.selectGuideList(vo);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);	
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);
		return rMap;
	}

	@Override
	public int insertCheckGuide(LikeVO vo) {
		int cnt =likeTable.selectCheckGuide(vo);
		if(cnt == 0) {
			likeTable.insertCheckGuide(vo);
			return 1;
		}
		likeTable.deleteCheckGuide(vo);
		return -1;
	}

	@Override
	public int selectCheck(LikeVO vo) {
		int res =likeTable.checkCountGuide(vo);
		
		return res;
	}
	@Override
	public Map<String, Object> selectUserGuideList(UserInfoVO user) {
		int currentPageNo = user.getCurrentPageNo();
		final int pageRange= 10;
		final int listRange= 10;
		int startPageNo =1;
		int lastPageNo = pageRange;
		if(currentPageNo > (pageRange/2)) {
            startPageNo = currentPageNo - ((lastPageNo/2)-1);
            lastPageNo += (startPageNo-1);
        }
		int listSize =guideBoard.selectUserGuideCnt(user);
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow =currentPageNo*listRange;
		int endPageNo = (listSize-1)/listRange+1;
		
		if(endPageNo<11) {
			lastPageNo=endPageNo;
		}
		PageVO page = new PageVO();
		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		user.setPage(page);
		List<GuideBoardVO> rList =guideBoard.selectUserGuideList(user);
		List<PageVO> pList = new ArrayList<>();
		pList.add(page);
		Map<String , Object> rMap = new HashMap<>();
		rMap.put("rList", rList);
		rMap.put("pList",pList);
		return rMap;
	}
	@Override
	public List<GuideBoardVO> selectNewList() {
		List<GuideBoardVO>rList = guideBoard.selectNewList();
		GuideBoardVO fb = new GuideBoardVO();
		fb.setGuideTitle("게시글이 없습니다");
		if(rList.size()<8) {
		for(int i=rList.size();i<=7;i++) {
			rList.add(i, fb);
		}
		}
		return rList;
	}

	@Override
	public int checkGuideBoardDelete(List<Integer> deleteList) {
		for(int idx : deleteList) {
			GuideBoardVO vo = new GuideBoardVO();
			LikeVO likeVO =new LikeVO();
			vo.setGuideNum(idx);
			likeVO.setFreeNum(idx);
			if(guideBoard.selectGuideFilePath(vo)!=null) {
				String fileName =guideBoard.selectGuideFilePath(vo).getGuideFilepath();
				File f = new File(filePath + fileName);
				if (f.exists()) {
					f.delete();
				}
			}				
			guideCnt.deleteGuideCnt();
			likeTable.deleteGuideAll(likeVO);
			int cnt = tagTable.deleteGuideAll(vo);
			guideBoard.deleteGuideBoard(vo);
		}

		return 0;
	}

	@Override
	public Map<String, Object> selectBestGuideList(int cnt) {
		PageVO page = new PageVO();
		GuideBoardVO vo = new GuideBoardVO();
		final int listRange = 7;
		int currentPageNo = cnt;
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow = listRange*currentPageNo;
		int listSize = guideBoard.selectBestGuideeListCount();
		int endPageNo = (listSize - 1) / listRange + 1;
		page.setEndPageNo(endPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setCurrentPageNo(currentPageNo);	
		vo.setPage(page);
		List<GuideBoardVO>rList = guideBoard.selectBestGuideList(vo);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);
		GuideBoardVO fb = new GuideBoardVO();
		fb.setGuideTitle("인기글이 없습니다");
		if(rList.size()<7) {
		for(int i=rList.size();i<=6;i++) {
			rList.add(i, fb);
			}
		}
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);;
		return rMap;
	}



}
