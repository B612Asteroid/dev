package com.song.dev.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				
				List<TeamBean> teams = teamMapper.findTeamsByName(teamStrs);
				TeamBean winTeam = teams.get(0);
				TeamBean loseTeam = teams.get(1);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				Date date = format.parse(dateStr);
				
				LCKBean lck = LCKBean.builder()
					.winTeam(winTeam)
					.loseTeam(loseTeam)
					.gameDate(new Timestamp(date.getTime()))
					.season(findSeason)
					.result(result)
					.build();
				
				lcks.add(lck);
			}
			
			lckMapper.insertLCKs(lcks);
			System.out.println(lcks.size() + "개 입력 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
