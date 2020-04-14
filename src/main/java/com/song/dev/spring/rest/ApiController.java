package com.song.dev.spring.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@GetMapping("/hello")
	public Map<String, Object> hello() {
		Map<String, Object> result = new HashMap<>();
		result.put("message", "This is The first Spring boot APP!");
		
		return result;
	}
	
	
}
