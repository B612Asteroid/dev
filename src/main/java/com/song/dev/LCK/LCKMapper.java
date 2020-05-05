package com.song.dev.LCK;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * LCK CRUD 관련 Mapper For Mybatis
 * @author 송기범
 *
 */
@Mapper
public interface LCKMapper {
	
	/**
	 * insert LCK
	 * @return
	 */
	public void insertLCK(LCKBean lck);
	
	/**
	 * insert Many LCK
	 * @return
	 */
	public void insertLCKs(List<LCKBean> lcks);
	
	/**
	 * find All LCK Matches
	 */
	public List<LCKBean> findAllLCKs();
	
	/**
	 * find LCK Matches By SeasonYear
	 */
	public List<LCKBean> findLCKBySeason(Map<String, Object> paramMap);
}
