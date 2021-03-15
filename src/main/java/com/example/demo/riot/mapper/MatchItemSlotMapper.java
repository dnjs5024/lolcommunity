package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.MatchInfoVO;
import com.example.demo.riot.vo.MatchItemSlotVO;

@Mapper
public interface MatchItemSlotMapper {
	int insertMatchItemSlot (MatchItemSlotVO items);
	List<MatchItemSlotVO> selectMatchItemSlotList (MatchItemSlotVO items);
	MatchItemSlotVO selectMatchItemSlotByKey (MatchItemSlotVO mis);
}
