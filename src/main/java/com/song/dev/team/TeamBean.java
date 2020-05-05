package com.song.dev.team;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Alias("teamBean")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
