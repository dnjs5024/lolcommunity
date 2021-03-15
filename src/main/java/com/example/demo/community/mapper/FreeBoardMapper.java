package com.example.demo.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

@Mapper
public interface FreeBoardMapper {
   int writeFreeBoard(FreeBoardVO vo);
   List<FreeBoardVO>selectFreeList(FreeBoardVO vo);
   int selectFreeNum();
   FreeBoardVO selectFreeOn(FreeBoardVO vo);
   int deleteFreeBoard(FreeBoardVO vo);
   int updateFreeBoard(FreeBoardVO vo);
   int freeboardCnt(FreeBoardVO vo);
   int deleteFreeBoardCnt(FreeBoardVO vo);
   void freeboardTagCnt(TagTableVO vo);
   void freeboardTagCntDelete(TagTableVO vo);
   int selectSearchSize(FreeBoardVO vo);
   void deleteFreeBoardUser(UserInfoVO user);
   int selectFreeNumUser(UserInfoVO user);
   List<FreeBoardVO>selectUserFreeList(UserInfoVO user);
   int selectUserFreeCnt(UserInfoVO user);
   List<FreeBoardVO>selectNewList();
   FreeBoardVO selectFreeFilePath(FreeBoardVO vo);
   List<FreeBoardVO>selectBestFreeList(FreeBoardVO vo);
   int selectBestFreeListCount();
}
