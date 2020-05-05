package com.song.dev.LCK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("lckService")
public class LCKServiceImpl implements LCKService {

	@Autowired
	private LCKMapper lckMapper;
	
	@Override
	public List<LCKBean> getLCKs(int seasonYear, String season) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seasonYear", seasonYear);
		paramMap.put("season", season);
		
		List<LCKBean> list = lckMapper.findLCKBySeason(paramMap);
		return list;
	}

}
