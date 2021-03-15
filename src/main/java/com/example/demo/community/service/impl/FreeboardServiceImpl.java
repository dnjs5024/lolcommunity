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

import com.example.demo.community.mapper.FreeBoardMapper;
import com.example.demo.community.mapper.FreeCnt;
import com.example.demo.community.mapper.GuideBoardMapper;
import com.example.demo.community.mapper.LikeTable;
import com.example.demo.community.mapper.TagTable;
import com.example.demo.community.mapper.UserInfoMapper;
import com.example.demo.community.service.FreeBoardService;
import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.FreeCntVO;
import com.example.demo.community.vo.LikeVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FreeboardServiceImpl implements FreeBoardService {
	String filePath = "C:\\Users\\user\\git\\ndibsg2\\src\\main\\webapp\\resources\\freeBoardimg\\";
	@Resource
	FreeBoardMapper freeBoard;

	@Resource
	FreeCnt freeCnt;

	@Resource
	LikeTable likeTable;

	@Resource
	TagTable tagTable;

	@Resource
	UserInfoMapper userInfoMapper;

	@Resource
	GuideBoardMapper guideBoardMapper;

	@Override
	public int writeFreeBoard(FreeBoardVO vo) {
		UserInfoVO user = new UserInfoVO();
		if (vo.getFreeFile().getSize() != 0) {
			MultipartFile file = vo.getFreeFile();
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String transName = System.nanoTime() + extName;
			vo.setFreeFilepath(transName);
			File f = new File(filePath + transName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}

			user.setUserNum(vo.getUserNum());
			userInfoMapper.userAddExperience(user);
			user = userInfoMapper.selectUserExperience(user);
			int userExperience = user.getUserExperience();
			if (userExperience >= 100) {
				userInfoMapper.userUpLever(user);
				userInfoMapper.userCleanExperience(user);
			}

			return freeBoard.writeFreeBoard(vo);
		}
		user.setUserNum(vo.getUserNum());
		userInfoMapper.userAddExperience(user);
		user = userInfoMapper.selectUserExperience(user);
		int userExperience = user.getUserExperience();
		if (userExperience >= 100) {
			userInfoMapper.userUpLever(user);
			userInfoMapper.userCleanExperience(user);
		}
		int cnt = freeBoard.writeFreeBoard(vo);
		return cnt;
	}

	@Override
	public Map<String, Object> selectFreeList(PageVO page) {
		int currentPageNo = page.getCurrentPageNo();

		final int pageRange = 10;
		final int listRange = 20;
		int startPageNo = 1;
		int lastPageNo = pageRange;
		if (currentPageNo > (pageRange / 2)) {
			startPageNo = currentPageNo - ((lastPageNo / 2) - 1);
			lastPageNo += (startPageNo - 1);
		}
		int listSize = freeBoard.selectFreeNum();// 50
		int startRow = (currentPageNo - 1) * listRange + 1;
		int endRow = currentPageNo * listRange;
		int endPageNo = (listSize - 1) / listRange + 1;

		if (endPageNo < 11) {
			lastPageNo = endPageNo;
		}

		page.setStartPageNo(startPageNo);
		page.setLastPageNo(lastPageNo);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setListSize(listSize);
		page.setEndPageNo(endPageNo);
		page.setListRange(listRange);
		FreeBoardVO vo = new FreeBoardVO();
		vo.setPage(page);
		List<FreeBoardVO> rList = freeBoard.selectFreeList(vo);
		List<PageVO> pList = new ArrayList<>();
		pList.add(page);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("rList", rList);
		rMap.put("pList", pList);
		return rMap;
	}

	@Override
	public FreeBoardVO selectFreeOne(FreeBoardVO vo, HttpSession hs) {

		FreeCntVO freeCntVO = new FreeCntVO();
		freeCntVO.setSessionKey(hs.getId());
		freeCntVO.setFreeBoard(vo);

		int cnt = freeCnt.selectAddCnt(freeCntVO);

		if (cnt != 1) {
			 freeBoard.freeboardCnt(vo);
			freeCnt.freeAddCnt(freeCntVO);

		}
		FreeBoardVO res = freeBoard.selectFreeOn(vo);

		return res;
	}

	@Override
	public int deleteFreeBoard(FreeBoardVO vo) {
		LikeVO likeVO = new LikeVO();
		likeVO.setFreeNum(vo.getFreeNum());
		File f = new File(filePath + vo.getFreeFilepath());
		if (f.exists()) {
			f.delete();
		}
		freeCnt.deleteFreeCnt();
		likeTable.deleteAll(likeVO);
		int cnt = tagTable.deleteAll(vo);
		log.info("cnt=={}", cnt);
		return freeBoard.deleteFreeBoard(vo);
	}

	@Override
	public int updateFreeBoard(FreeBoardVO vo) {

		File f = new File(filePath + vo.getFreeFilepath());
		if (f.exists()) {
			f.delete();
		}
		if (vo.getFreeFile().getSize() != 0) {
			MultipartFile file = vo.getFreeFile();
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String transName = System.nanoTime() + extName;
			vo.setFreeFilepath(transName);
			File fPath = new File(filePath + transName);
			try {
				file.transferTo(fPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return freeBoard.updateFreeBoard(vo);
		}
		return freeBoard.updateFreeBoard(vo);
	}

	@Override
	public int insertCheck(LikeVO vo) {

		int cnt = likeTable.selectCheck(vo);
		if (cnt == 0) {
			likeTable.insertCheck(vo);
			return 1;
		}
		likeTable.deleteCheck(vo);
		return -1;
	}

	@Override
	public int selectCheck(LikeVO vo) {
		int res = likeTable.checkCount(vo);

		return res;
	}

	@Override
	public Map<String, Object> selectSearchList(FreeBoardVO vo, int currentPageNo) {
		PageVO page = new PageVO();
		page.setCurrentPageNo(currentPageNo);
		final int pageRange = 10;
		final int listRange = 20;
		int startPageNo = 1;
		int lastPageNo = pageRange;
		if (currentPageNo > (pageRange / 2)) {

			startPageNo = currentPageNo - ((lastPageNo / 2) - 1);

			lastPageNo += (startPageNo - 1);
		}
		int listSize = freeBoard.selectSearchSize(vo);
		log.info("listSize==>{}", listSize);
		int startRow = (currentPageNo - 1) * listRange + 1;
		int endRow = currentPageNo * listRange;
		int endPageNo = (listSize - 1) / listRange + 1;
		if (endPageNo < 11) {
			lastPageNo = endPageNo;
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
		List<FreeBoardVO> rList = freeBoard.selectFreeList(vo);
		List<PageVO> pList = new ArrayList<>();
		pList.add(page);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("rList", rList);
		rMap.put("pList", pList);
		System.out.println(rMap);
		return rMap;
	}

	@Override
	public int selectFreeNumUser(UserInfoVO user) {
		int cnt = freeBoard.selectFreeNumUser(user);
		cnt += guideBoardMapper.selectGuideNumUser(user);
		return cnt;
	}

	@Override
	public Map<String, Object> selectUserFreeList(UserInfoVO user) {
		log.info("user=={}", user);
		int currentPageNo = user.getCurrentPageNo();
		final int pageRange = 10;
		final int listRange = 10;
		int startPageNo = 1;
		int lastPageNo = pageRange;
		if (currentPageNo > (pageRange / 2)) {
			startPageNo = currentPageNo - ((lastPageNo / 2) - 1);
			lastPageNo += (startPageNo - 1);
		}
		int listSize = freeBoard.selectUserFreeCnt(user);
		int startRow = (currentPageNo - 1) * listRange + 1;
		int endRow = currentPageNo * listRange;
		int endPageNo = (listSize - 1) / listRange + 1;

		if (endPageNo < 11) {
			lastPageNo = endPageNo;
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
		List<FreeBoardVO> rList = freeBoard.selectUserFreeList(user);
		List<PageVO> pList = new ArrayList<>();
		pList.add(page);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("rList", rList);
		rMap.put("pList", pList);
		return rMap;
	}

	@Override
	public List<FreeBoardVO> selectNewList() {
		List<FreeBoardVO> rList = freeBoard.selectNewList();
		FreeBoardVO fb = new FreeBoardVO();
		fb.setFreeTitle("게시글이 없습니다");
		if (rList.size() < 8) {
			for (int i = rList.size(); i <= 7; i++) {
				rList.add(i, fb);
			}
		}
		return rList;
	}

	@Override
	public int checkFreeBoardDelete(List<Integer> deleteList) {			
		for(int idx : deleteList) {
			FreeBoardVO vo = new FreeBoardVO();
			LikeVO likeVO =new LikeVO();
			vo.setFreeNum(idx);
			likeVO.setFreeNum(idx);
			if(freeBoard.selectFreeFilePath(vo)!=null) {
				String fileName =freeBoard.selectFreeFilePath(vo).getFreeFilepath();
				File f = new File(filePath + fileName);
				if (f.exists()) {
					f.delete();
				}
			}				
			freeCnt.deleteFreeCnt();
			likeTable.deleteAll(likeVO);
			int cnt = tagTable.deleteAll(vo);
			freeBoard.deleteFreeBoard(vo);
		}

		return 0;
	}

	@Override
	public Map<String,Object> selectBestFreeList(int cnt) {
		PageVO page = new PageVO();
		FreeBoardVO vo = new FreeBoardVO();
		final int listRange = 7;
		int currentPageNo = cnt;
		int startRow = (currentPageNo-1)*listRange+1;
		int endRow = listRange*currentPageNo;
		int listSize = freeBoard.selectBestFreeListCount();
		int endPageNo = (listSize - 1) / listRange + 1;
		page.setEndPageNo(endPageNo);
		page.setListSize(listSize);
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		page.setCurrentPageNo(currentPageNo);	
		vo.setPage(page);
		List<FreeBoardVO> rList = freeBoard.selectBestFreeList(vo);
		List<PageVO> pList =new ArrayList<>();
		pList.add(page);	
		FreeBoardVO fb = new FreeBoardVO();
		fb.setFreeTitle("인기글이 없습니다");
		if (rList.size() < 7) {
			for (int i = rList.size(); i <= 6; i++) {
				rList.add(i, fb);
			}
		}
		Map<String,Object>rMap= new HashMap<>();
		rMap.put("rList",rList);
		rMap.put("pList",pList);
		return rMap;
	}

	

}
