package com.song.dev.LCK;

import java.sql.Timestamp;

import com.song.dev.season.SeasonBean;
import com.song.dev.team.TeamBean;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class LCKBean {
	
	/** pk */
	private final long id;
	
	/** Winner */
	private TeamBean winTeam;
	
	/** Loser */
	private TeamBean loseTeam;
	
	/** Game Date*/
	private Timestamp gameDate;
	
	/** Match result */
	private String result;
	
	/** LCK Match Season */
	private SeasonBean season;
	
}
