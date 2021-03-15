package com.example.demo.riot.utill;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

public class GameTime {
public String playTime(long timeValue) {
		
		long playMin = (long) Math.floor(timeValue/60);
		long playSec = timeValue % 60;
		if(playMin<1) {
			return (playSec+"초");
		}else {
			return (playMin+"분 "+playSec+"초");
		}
	}
	
	public String betweenTime(long timeValue) {
		Date date = new Date();
		long today = date.getTime();
		long betweenSec = (today - timeValue) / 1000;
		long betweenTime = (long) Math.floor( betweenSec/ 60);
	        if (betweenTime < 1) {
	        	return (betweenSec+"초 전");
	        }
	        if (betweenTime < 60) {
	        	return (betweenTime+"분 전");
	        }

	        long betweenTimeHour = (long) Math.floor(betweenTime / 60);
	        if (betweenTimeHour < 24) {
	        	return (betweenTimeHour+"시간 전");
	        }

	        long betweenTimeDay = (long) Math.floor(betweenTime / 60 / 24);
	        if (betweenTimeDay < 365) {
	        	if(betweenTimeDay == 1) {
	        		return("하루 전");
	        	}
	        	return (betweenTimeDay+"일 전");
	        }
		return  ((long) Math.floor(betweenTimeDay / 365)+"년 전");
	}
}
