package org.examportal.exam.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public CategoryResponseDto save(CategoryRequestDto request) {
    Category category = categoryMapper.toEntity(request);

    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  public List<CategoryResponseDto> findAll() {
    return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
  }

  public CategoryResponseDto findById(Long id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Category not found with id " + id));

    return categoryMapper.toDto(category);
  }

  public CategoryResponseDto update(Long id, CategoryRequestDto request) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Category not found with id " + id));

    category.setTitle(request.title());
    category.setDescription(request.description());

    Category updatedCategory = categoryRepository.save(category);
    return categoryMapper.toDto(updatedCategory);
  }

  public void deleteById(Long id) {
    categoryRepository
        .findById(id)
        .orElseThrow(() -> new IllegalStateException("Category not found with id " + id));
    categoryRepository.deleteById(id);
  }
}
