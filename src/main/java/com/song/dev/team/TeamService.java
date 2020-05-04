package com.song.dev.team;

import java.io.IOException;
import java.util.List;

/**
 * Team 관련 서비스  Interface
 * @author 송기범
 *
 */
public interface TeamService {
	
	/**
	 * LCK 팀을 등록합니다.
	 * @return
	 */
	public TeamBean insertTeam(TeamBean team);
	
	/**
	 * LCK 팀 여러 건을 등록합니다.
	 */
	public List<TeamBean> insertTeams() throws IOException;
	
	/**
	 * LCK 팀 리스트를 반환합니다.
	 * @return
	 */
	public List<TeamBean> getTeams();
	
	/**
	 * 팀 이름으로 팀 정보를 검색합니다.
	 */
	public List<TeamBean> getTeamsByName(List<String> teamNames);
}
