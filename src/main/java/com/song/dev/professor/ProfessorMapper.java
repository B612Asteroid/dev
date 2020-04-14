package com.song.dev.professor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfessorMapper {
	
	public List<ProfessorBean> findAllProfessors();
}
