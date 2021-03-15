package com.example.demo.community.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.community.service.LoLNewsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoLNewsServiceImpl implements LoLNewsService {
	
	public static StringBuilder sb;
	@Override
	public Map<String, Object> LoLNewsList(int cnt) {
		Map<String,Object> rMap = new HashMap<>();
		Map<String,Object> gMap = new HashMap<>();
		String clientId = "KLmy8SB9oPjCwb5I6gLO";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "RIhB4Nmdp0";// 애플리케이션 클라이언트 시크릿값";\
        int display = 5; // 검색결과갯수. 최대100개
        try {
            String text = URLEncoder.encode("리그오브레전드", "utf-8");
            String date = "sim";
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=" + display 
            		+ "&sort="+date;	 
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;
 
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
//            log.info("sb==>{}",sb);
            br.close();
            con.disconnect();
            ObjectMapper om = new ObjectMapper();
               Map<String,Object> map = om.readValue(sb.toString(),Map.class);
               Iterator<String> it = map.keySet().iterator();
               while(it.hasNext()) {
                  String key = it.next();
                  Object value = map.get(key);
//                  log.info("{}==>{}",key,value);
               }
               List<Map<String,Object>> list = (List<Map<String,Object>>)map.get("items"); 
               int i = 1;
//               log.info("list==>{}",list);
               for(Map<String,Object>map2 : list) {  	
              	 gMap.put("rList"+i,map2 );
                     i+=1;
               }
              rMap.put("rList",gMap.get("rList"+cnt));
//                 log.info("rMap==>{}",rMap);
        } catch (Exception e) {
            System.out.println(e);
        }
 
		return rMap;
	}
	@Override
	public Map<String, Object> LoLNewsImg(int cnt) {
		Map<String,Object> rMap = new HashMap<>();
		Map<String,Object> gMap = new HashMap<>();
        String clientId = "KLmy8SB9oPjCwb5I6gLO";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "RIhB4Nmdp0";// 애플리케이션 클라이언트 시크릿값";\
        int display = 5; // 검색결과갯수. 최대100개
        try {
            String text = URLEncoder.encode("라이엇게임즈", "utf-8");
            String date = "sim";
            String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text + "&display=" + display 
            		+ "&sort="+date;	 
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;
 
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();
            ObjectMapper om = new ObjectMapper();
               Map<String,Object> map = om.readValue(sb.toString(),Map.class);
               System.out.println(map);
               Iterator<String> it = map.keySet().iterator();
               while(it.hasNext()) {
                  String key = it.next();
                  Object value = map.get(key);
                  System.out.println(key);
                  System.out.println(value);
//                  log.info("{}==>{}",key,value);
               }
               List<Map<String,Object>> list = (List<Map<String,Object>>)map.get("items");          
               int i = 1;
//             log.info("list==>{}",list);
             for(Map<String,Object>map2 : list) {  	
            	 gMap.put("rList"+i,map2 );
                   i+=1;
             }
            rMap.put("rList",gMap.get("rList"+cnt));
//               log.info("rMap1==>{}",rMap);
        } catch (Exception e) {
            System.out.println(e);
        }
		return rMap;
	}
	public static void main(String []args) {
		LoLNewsService a = new LoLNewsServiceImpl();
		
		System.out.println(a.LoLNewsImg(1));
	}
}
