package com.example.demo.riot.utill;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	public String filter(Map<String, Object> spellInfo) {
		String spellsContent = spellInfo.get("tooltip").toString();
		Iterator<String> keys = spellInfo.keySet().iterator();
		List<String> effectBurn = null;
		String cooldownBurn = null;
		String costBurn = null;
		String rangeBurn = null;
		List<Map<String,Object>> vars = null;
		while(keys.hasNext()) {
			String key = keys.next();
			if(key.equals("effectBurn")) {
				effectBurn = (List<String>) spellInfo.get("effectBurn");
			}else if(key.equals("cooldownBurn")){
				cooldownBurn = (String) spellInfo.get("cooldownBurn");
			}else if(key.equals("costBurn")){
				costBurn =  (String) spellInfo.get("costBurn");
			}else if(key.equals("rangeBurn")){
				rangeBurn =  (String) spellInfo.get("rangeBurn");
			}else if(key.equals("vars")){
				vars =  (List<Map<String,Object>>) spellInfo.get("vars");
			}
			
		}
		Pattern pattern = Pattern.compile("\\{\\{(.+?)\\}\\}");
		Matcher matcher = pattern.matcher(spellsContent);
		int eIdx = 0;
		while (matcher.find()) {
			if (matcher.group(1).trim().indexOf("e") == 0 &&  matcher.group(1).trim().length() == 2) {
				eIdx= Integer.parseInt((matcher.group(1)).trim().substring(1));
				if(eIdx != 0) {
					spellsContent = spellsContent.replace(matcher.group(0), effectBurn.get(eIdx));
				}else {
					spellsContent = spellsContent.replace(matcher.group(0), "");
				}
				continue;
			} else if ((matcher.group(1)).trim().indexOf("a") == 0 &&  matcher.group(1).trim().length() == 2) {
				for(Map<String,Object> var : vars) {
					if((matcher.group(1).trim()).equals(var.get("key"))) {
						spellsContent = spellsContent.replace(matcher.group(0), (int)(Double.parseDouble(var.get("coeff").toString())*100)+"");
					}
				}
				continue;	
			} else if (matcher.group(1).indexOf("cost") == 1) {
				spellsContent = spellsContent.replace(matcher.group(0), costBurn);
				continue;
			}
			spellsContent = spellsContent.replace(matcher.group(0), "?");
		}
//		spellsContent = spellsContent.replace("<br />", "\n");
//		pattern = Pattern.compile("<scaleAP>(.+?)</scaleAP>");
//		matcher = pattern.matcher(spellsContent);
		while (matcher.find()) {
			spellsContent = spellsContent.replace(matcher.group(1), matcher.group(1).replace(")", "% 주문력)"));
		}
		pattern = Pattern.compile("<scaleAD>(.+?)</scaleAD>");
		matcher = pattern.matcher(spellsContent);
		while (matcher.find()) {
			spellsContent = spellsContent.replace(matcher.group(1), matcher.group(1) + "% 공격력");
		}
//		pattern = Pattern.compile("<(.+?)>");
//		matcher = pattern.matcher(spellsContent);
//		while (matcher.find()) {
//			spellsContent = spellsContent.replace(matcher.group(0), "");
//		}
		return spellsContent;
	}

	public static void main(String[] args) {

	}
}
