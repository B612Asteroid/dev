package com.song.dev.LCK;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.song.dev.season.SeasonBean;
import com.song.dev.team.TeamBean;

import lombok.Data;

@Alias("lckBean")
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class LCKBean {
	
	/** pk */
	private long id;
	
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
