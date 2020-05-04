/**
 * 
 */
package com.song.dev.team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dev.jsoup.JsoupParsor;

/**
 * @author 송기범
 *
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService {

	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	
	@Autowired
	private TeamMapper teamMapper;
	
	
	
	@Override
	public TeamBean insertTeam(TeamBean team) {
		teamMapper.insertTeam(team);
		return team;
	}

	@Override
	public List<TeamBean> getTeams() {
		
		List<TeamBean> teams = teamMapper.findAllTeams();
		return teams;
	}

	@Override
	public List<TeamBean> insertTeams() throws IOException {
		JsoupParsor parsor = new JsoupParsor();
		
		List<Map<String, Object>> nodes = parsor.parseLck();
		Set<String> teamset = getDistinctNode(nodes);
		logger.info(teamset.toString());
		
		List<TeamBean> teams = getInsertTeams(teamset);
		
		teamMapper.insertTeams(teams);
		
		return teams;
	}
	
	
	@Override
	public List<TeamBean> getTeamsByName(List<String> teamNames) {
		List<TeamBean> teams = teamMapper.findTeamsByName(teamNames);
		return teams;
	}
	
	
	/////////////////////////////////// private ////////////////////////////
	
	

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
	
	// #. Team Name들이 들어있는 SET을  TeamBean으로 변환
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
