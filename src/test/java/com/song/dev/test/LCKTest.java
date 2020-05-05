package com.song.dev.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.song.dev.LCK.LCKBean;
import com.song.dev.LCK.LCKMapper;
import com.song.dev.jsoup.JsoupParsor;
import com.song.dev.season.SeasonBean;
import com.song.dev.season.SeasonMapper;
import com.song.dev.team.TeamBean;
import com.song.dev.team.TeamMapper;

import ch.qos.logback.classic.Logger;

@SpringBootTest
public class LCKTest {
	
	@Autowired
	private SeasonMapper seasonMapper;
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Autowired
	private LCKMapper lckMapper;
	
	
	
	@Test
	public void LCKInsertTest() {	
		try {
			// #. season 검색
			SeasonBean season = new SeasonBean();
			season.setSeasonYear(2020);
			
			SeasonBean findSeason = seasonMapper.findSeason(season);
			
			// #. URL 파싱한거 가져옴
			JsoupParsor parsor = new JsoupParsor();
			List<Map<String, Object>> jsonLcks = parsor.parseLck();
			
			List<LCKBean> lcks = new ArrayList<>();
			for (Map<String, Object> map : jsonLcks) {
				String dateStr = (String)map.get("date");
				String result = (String)map.get("result");
				List<String> teamStrs = (List<String>)map.get("team");
				
				// #. 1-2, 0-2 방식을 치환해서 2-0, 2-1 형식으로 변환
				int winIdx = 0;
				int loseIdx = 1;
				
				String[] temp = result.split("-");
				
				if (temp.length != 1) {
					int winScore = Integer.parseInt(temp[0]);
					int loseScore = Integer.parseInt(temp[1]);
					
					if (winScore < loseScore) {
						result = loseScore + "-" + winScore;
						winIdx = 1;
						loseIdx = 0;
					}
				}
				
				List<TeamBean> teams = teamMapper.findTeamsByName(teamStrs);
				TeamBean winTeam = teams.get(winIdx);
				TeamBean loseTeam = teams.get(loseIdx);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
				Date date = format.parse(dateStr);
				
				LCKBean lck = new LCKBean();
				lck.setWinTeam(winTeam);
				lck.setLoseTeam(loseTeam);
				lck.setGameDate(new Timestamp(date.getTime()));
				lck.setSeason(findSeason);
				lck.setResult(result);
				    
				lcks.add(lck);
			}
			
			lckMapper.insertLCKs(lcks);
			System.out.println(lcks.size() + "개 입력 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getLckListTest() {
		try {
			// TODO Auto-generated method stub
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("seasonYear", 2020);
			paramMap.put("season", "Spring");
			
			List<LCKBean> list = lckMapper.findLCKBySeason(paramMap);
			System.out.println(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
