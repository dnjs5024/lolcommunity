package com.example.demo.riot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.riot.vo.ChampRotationVO;
import com.example.demo.riot.vo.ChampionInfoVO;

@Mapper
public interface ChampionRotationMapper {
	int insertChampionRotation (int championInfoKey);
	List<ChampionInfoVO> selectChampionRotation();
	int deleteChampionRotation();
}
