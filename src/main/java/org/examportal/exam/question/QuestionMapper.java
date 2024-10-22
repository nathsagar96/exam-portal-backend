package org.examportal.exam.question;

import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper {

    Question toEntity(QuestionRequestDto questionRequestDto);

    QuestionResponseDto toDto(Question question);
}
