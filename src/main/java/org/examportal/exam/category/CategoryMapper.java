package org.examportal.exam.category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  Category toEntity(CategoryRequestDto categoryRequestDto);

  CategoryResponseDto toDto(Category category);
}
