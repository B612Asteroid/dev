package com.song.dev.season;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Alias("seasonBean")
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class SeasonBean {
	
	/** id */
	private long id;
	
	/** LCK Season YEAR */
	private int seasonYear;
	
	/** LCK Season */
	private String season;
	
	/** 정규시즌, 플레이오프, 승강전 등... */
	private String league;
}
