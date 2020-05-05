package com.song.dev.LCK;

import java.util.List;

/**
 * LCK 조회용 Interface
 * @author 송기범
 *
 */
public interface LCKService {
	
	/**
	 * 해당 시즌의 LCK 리그 전체를 가져옵니다.
	 * @param seasonYear 년도
	 * @param season 스프링 / 서머 / 롤드컵 시즌
	 * @return
	 */
	public List<LCKBean> getLCKs(int seasonYear, String season);
}
