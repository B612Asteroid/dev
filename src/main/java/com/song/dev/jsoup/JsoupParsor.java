package com.song.dev.jsoup;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.song.dev.util.ArrayUtil;

/**
 * JSoup Parsor
 * @author 송기범
 *
 */
public class JsoupParsor {
	
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(JsoupParsor.class);
	private static final String URL = "https://kr.betsapi.com/l/13988/LOL--LCK-Spring"; 
	 
	/**
	 * LCK 경기결과를 파싱해서 JSON으로 반환함.
	 * TODO URL을 받아서 하는 방식으로 변경해야 함.(LCK spring, summer 등..)
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> parseLck() throws IOException {
		
		logger.info("--------- Method urlParsing start -----------");
		List<Map<String, Object>> lckList = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			Document doc = Jsoup.connect(URL + "/p." + i).get();
			
			// #. tbody를 끌고 왔으니 이걸 JSON으로 변경해보자
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
				
				if(dateStr.startsWith("2019")) {
					logger.info(dateStr + "는 이전 시즌");
					break;
				}
				
				map.put("date", dateStr);
				map.put("team", teams);
				map.put("result", result);
				list.add(map);
			}
			lckList.addAll(list);
		}
		return lckList;
	}
	
	
	/////////////////////////// private ////////////////////////
	
	
	// #. 경기 날짜를 가져온다.
	private String getDate(Element element) {
		Elements dt_n = element.getElementsByClass("dt_n");
		String dateStr = dt_n.attr("data-dt");
		
		return dateStr;
	}
	
	// #. 경기한 양 팀을 가져온다.
	private List<String> getTeams(Element element) {
		Elements teamAnycs = element.getElementsByAttributeValueStarting("href", "/t");
		List<String> teams = new ArrayList<>();
		for (Element anyc : teamAnycs) {
			String team = anyc.text();
			teams.add(team);
		}
		
		return teams;
	}
	
	// #. 경기 결과를 가져온다.
	private String getResult(Element element) {
		Elements resultAnycs = element.getElementsByAttributeValueStarting("href", "/r");
		String result = resultAnycs.text();
		
		return result;
	}
}
