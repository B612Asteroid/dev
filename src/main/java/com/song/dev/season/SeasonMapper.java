package com.song.dev.season;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * Season Mapper
 * @author 송기범
 *
 */
@Mapper
public interface SeasonMapper {
	
	/**
	 * Season 객체를 검색하여 가져온다.
	 * 검색 조건이 null이면 WHERE절에 포함이 되지 않는다
	 * @return
	 */
	public SeasonBean findSeason(SeasonBean season); 
	
	/**
	 * season 1건을 입력
	 * @param season
	 */
	public void insertSeason(SeasonBean season);
	
	/**
	 * season 여러건을 입력
	 * @param season
	 */
	public void insertSeasons(List<SeasonBean> season);
	
}
