package com.song.dev.team;

import java.sql.Timestamp;

import lombok.Data;

public @Data class TeamBean {
	
	/** PK */
	private long id;
	
	/** team name */
	private String teamName;
	
	/** create date */
	private Timestamp createDate;
	
	/** update date */
	private Timestamp updateDate;
}
