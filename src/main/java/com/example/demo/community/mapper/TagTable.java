package com.example.demo.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.community.vo.FreeBoardVO;
import com.example.demo.community.vo.GuideBoardVO;
import com.example.demo.community.vo.TagTableVO;
import com.example.demo.community.vo.UserInfoVO;

@Mapper
public interface TagTable {
	int insertTag(TagTableVO vo);
	TagTableVO selectTagOne(TagTableVO vo);
	int deleteTag(TagTableVO vo);
	int updateTag(TagTableVO vo);
	List<TagTableVO> selectTagList(TagTableVO vo);
	int selectTagCnt(TagTableVO vo);
	int selectGhostPwd(TagTableVO vo);
	int updateGhostTag(TagTableVO vo);
	int deleteAll(FreeBoardVO vo);
	int deleteGuideAll(GuideBoardVO vo);
	void freeboardTagCnt(TagTableVO vo);
	int updateTagNick(UserInfoVO user);
    void deleteTagUser(UserInfoVO user);
    int selectTagCntUser(UserInfoVO user);
    List<TagTableVO> selectTagUserList(UserInfoVO user);
    int selectGuideTagCntUser(UserInfoVO user);
    List<TagTableVO> selectGuideTagUserList(UserInfoVO user);
    
    int insertGuideTag(TagTableVO vo);
    List<TagTableVO>selectGuideTagList(TagTableVO vo);
    TagTableVO selectGuideTagOne(TagTableVO vo);
    int updateGuideTag(TagTableVO vo);
    int selectGuideGhostPwd(TagTableVO vo);
    int updateGuideGhostTag(TagTableVO vo);
    int deleteGuideTag(TagTableVO vo);
    int checkFreeTagDelete(TagTableVO vo);
    int checkGuideTagDelete(TagTableVO vo);
    
   
}
