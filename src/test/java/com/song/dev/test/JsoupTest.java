package com.song.dev.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JsoupTest {

	private static final Logger logger = LoggerFactory.getLogger(JsoupTest.class);
	
	private static final String URL = "https://kr.betsapi.com/l/13988/LOL--LCK-Spring"; 
	 
	@Test
	public void test() {
		try {
			Document doc = Jsoup.connect(URL).get();
			//logger.debug(doc.toString());
			
			// #. tbody를 끌고 왔으니 이걸 JSON으로 변경해보
			Elements trs = doc.select("tr");
			
			List<Map<String, Object>> list = new ArrayList<>();
			for (Element tr : trs) {
				
				Node child = tr.childNode(0);
				if ("th".equals(child.nodeName())) {
					continue;
				};
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				String dateStr = getDate(tr);
				List<String> teams = getTeams(tr);
				String result = getResult(tr);
				
				map.put("date", dateStr);
				map.put("team", teams);
				map.put("result", result);
				list.add(map);
			}
			
			logger.debug(list.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getDate(Element element) {
		Elements dt_n = element.getElementsByClass("dt_n");
		String dateStr = dt_n.attr("data-dt");
		
		return dateStr;
	}
	
	private List<String> getTeams(Element element) {
		Elements teamAnycs = element.getElementsByAttributeValueStarting("href", "/t");
		List<String> teams = new ArrayList<>();
		for (Element anyc : teamAnycs) {
			String team = anyc.text();
			teams.add(team);
		}
		
		return teams;
	}
	
	private String getResult(Element element) {
		Elements resultAnycs = element.getElementsByAttributeValueStarting("href", "/r");
		String result = resultAnycs.text();
		
		return result;
	}

}
