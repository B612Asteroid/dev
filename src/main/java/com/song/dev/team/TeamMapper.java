package com.song.dev.team;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper {
	
	/**
	 * Create Team
	 * @return
	 */
	public void insertTeam(TeamBean team);
	
	/**
	 * Return Team By Name
	 * @return
	 */
	public void insertTeams(List<TeamBean> teams);
	
	/**
	 * Return All Team
	 * @return
	 */
	public List<TeamBean> findAllTeams();
	
	/**
	 * Return Team By Name
	 * @return
	 */
	public List<TeamBean> findTeamsByName(List<String> teamNames);
	
	
}
