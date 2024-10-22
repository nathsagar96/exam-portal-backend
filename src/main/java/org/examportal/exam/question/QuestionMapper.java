package org.examportal.exam.question;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question toEntity(QuestionRequestDto questionRequestDto);

    QuestionResponseDto toDto(Question question);
}
