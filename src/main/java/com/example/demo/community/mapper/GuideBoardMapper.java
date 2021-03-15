package com.example.demo.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.PageVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

@Mapper
public interface GuideBoardMapper {
   int writeGuideBoard(GuideBoardVO vo);
   List<GuideBoardVO>selectGuideList(GuideBoardVO vo);
   int selectGuideNum();
   GuideBoardVO selectGuideOn(GuideBoardVO vo);
   int deleteGuideBoard(GuideBoardVO vo);
   int updateGuideBoard(GuideBoardVO vo);
   int guideBoardCnt(GuideBoardVO vo);
   int deleteGuideBoardCnt(GuideBoardVO vo);
   void guideBoardTagCnt(TagTableVO vo);
   void guideBoardTagCntDelete(TagTableVO vo);
   int selectSearchSize(GuideBoardVO vo);
   List<GuideBoardVO>selectUserGuideList(UserInfoVO user);
   int selectUserGuideCnt(UserInfoVO user);
   List<GuideBoardVO>selectNewList();
   int selectGuideNumUser(UserInfoVO user);
   GuideBoardVO selectGuideFilePath(GuideBoardVO vo);
   List<GuideBoardVO>selectBestGuideList(GuideBoardVO vo);
   int selectBestGuideeListCount();
}
