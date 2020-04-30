package com.song.dev.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtil {

	private static Logger logger = LoggerFactory.getLogger(ArrayUtil.class);
	
	
	public static void printList(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			logger.debug(map.toString());
		}
	}

}
