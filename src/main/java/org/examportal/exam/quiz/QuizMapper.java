package org.examportal.exam.quiz;

import org.mapstruct.Mapper;

@Mapper
public interface QuizMapper {

  QuizResponseDto toDto(Quiz quiz);

  Quiz toEntity(QuizRequestDto quizRequestDto);
}
