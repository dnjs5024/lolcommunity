package com.example.demo.riot.component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.riot.mapper.ChampionInfoMapper;
import com.example.demo.riot.vo.ChampionInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonChampParse {
	
	@Resource
	static ChampionInfoMapper cim;
	
	public static void main(String[] args) {
//		try {
//		    // create object mapper instance
//		    ObjectMapper mapper = new ObjectMapper();
//		    List<ChampionInfoVO> champList = new ArrayList<>();
//		    
//		    // convert JSON file to map
//		    Map<?, ?> map = mapper.readValue(Paths.get("D:\\java_study\\workspace\\team-project\\src\\main\\java\\com\\example\\demo\\riot\\component\\champion.json").toFile(), Map.class);
//		    Map<?, ?> rMap =  (Map<?, ?>) map.get("data");
//		    Map<?, ?> rMap2 = null;
//		    // print map entries
//		    for (Map.Entry<?, ?> entry : rMap.entrySet()) {
//		    	ChampionInfoVO champ = new ChampionInfoVO();
//		    	rMap2 = (Map<?, ?>) rMap.get(entry.getKey().toString());
//		    	champ.setChampionInfoId(rMap2.get("id").toString());
//		    	champ.setChampionInfoName(rMap2.get("name").toString());
//		    	champ.setChampionInfoKey(Integer.parseInt(rMap2.get("key").toString()));
//		    	champList.add(champ);
//		    }
//		    int cnt = 1;
//		    for(ChampionInfoVO map1 : champList) {
//		    	cim.insertChampionInfo(map1);
//		    }
//
//		} catch (Exception ex) {
//		    ex.printStackTrace();
//		}
	}
}
