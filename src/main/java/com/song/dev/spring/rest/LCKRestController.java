package com.song.dev.spring.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.song.dev.LCK.LCKBean;
import com.song.dev.LCK.LCKService;

@RestController
@RequestMapping("/lck")
public class LCKRestController {
	
	@Autowired
	@Qualifier("lckService")
	private LCKService lckService;
	
	@GetMapping("/getLck/{seasonYear}/{season}")
	public @ResponseBody List<LCKBean> getLckMatches(
			@PathVariable("seasonYear") int seasonYear, @PathVariable("season") String season) {
		return lckService.getLCKs(seasonYear, season);
	}
}
