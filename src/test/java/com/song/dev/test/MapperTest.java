package com.song.dev.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.song.dev.professor.ProfessorBean;
import com.song.dev.professor.ProfessorMapper;

@SpringBootTest
public class MapperTest {

	@Autowired
	ProfessorMapper professorMapper;
	
	@Test
	public void test() {
		List<ProfessorBean> list = professorMapper.findAllProfessors();
		System.out.println(list);
	}
	
	
}
