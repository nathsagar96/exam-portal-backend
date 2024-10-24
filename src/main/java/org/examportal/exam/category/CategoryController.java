package org.examportal.exam.category;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponseDto> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{categoryId}")
  @ResponseStatus(HttpStatus.OK)
  public CategoryResponseDto findById(@PathVariable final Long categoryId) {
    return categoryService.findById(categoryId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryResponseDto save(@RequestBody @Valid final CategoryRequestDto request) {
    return categoryService.save(request);
  }

  @PutMapping("/{categoryId}")
  @ResponseStatus(HttpStatus.OK)
  public CategoryResponseDto update(
      @PathVariable final Long categoryId, @RequestBody @Valid final CategoryRequestDto request) {
    return categoryService.update(categoryId, request);
  }

  @DeleteMapping("/{categoryId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long categoryId) {
    categoryService.deleteById(categoryId);
  }
}
