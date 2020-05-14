package com.song.dev.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.song.dev.jsoup.JsoupParsor;
import com.song.dev.team.TeamBean;
import com.song.dev.team.TeamService;

/**
 * TeamBean, Mapper 등 관련 Test Class
 * @author 송기범
 *
 */
@SpringBootTest
public class TeamTest {

	private static final Logger logger = LoggerFactory.getLogger(TeamTest.class);
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Test
	public void oneTeamInsert() {
		try {
			TeamBean team = new TeamBean();
			team.setTeamName("Test Team");
			teamService.insertTeam(team);
			
			logger.info("Team : " + team.getId() + " is created");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void teamInsert() {
		JsoupParsor parsor = new JsoupParsor();
		try {
			List<Map<String, Object>> nodes = parsor.parseLck();
			Set<String> teamset = getDistinctNode(nodes);
			logger.info(teamset.toString());
			
			List<TeamBean> teams = getInsertTeams(teamset);
			logger.info("Before insert == : " + teams.get(0).getId());
			
			teams = teamService.insertTeams();
			logger.info("after insert == : " + teams.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// #. List -> Set으로 변경해서 중복 제거
	private Set<String> getDistinctNode(List<Map<String, Object>> nodes) {
		Set<String> teamset = new HashSet<String>();
		for (Map<String, Object> node : nodes) {
			List<String> teamNodes = (List<String>)node.get("team");
			//logger.info("parsing Team : " + teamNodes.toString());
			teamset.addAll(teamNodes);
		}
		return teamset;
	}
	
	private List<TeamBean> getInsertTeams(Set<String> teamset) {
		
		List<TeamBean> insertList = new ArrayList<>();
		teamset.forEach((String teamName) -> {
			TeamBean team = new TeamBean();
			team.setTeamName(teamName);
			insertList.add(team);
		});
		
		return insertList;
	}
}
