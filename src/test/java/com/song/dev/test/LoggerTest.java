package com.song.dev.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoggerTest {

	Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	
	@Test
	void test() {
		logger.debug("Logger Class init");
	}

}
