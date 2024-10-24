package org.examportal.exam.quiz;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizzes")
@SecurityRequirement(name = "bearerAuth")
public class QuizController {
  private final QuizService quizService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<QuizResponseDto> findAll() {
    return quizService.findAll();
  }

  @GetMapping("/category/{categoryId}")
  @ResponseStatus(HttpStatus.OK)
  public List<QuizResponseDto> findAllByCategoryId(final Long categoryId) {
    return quizService.findAllByCategoryId(categoryId);
  }

  @GetMapping("/{quizId}")
  @ResponseStatus(HttpStatus.OK)
  public QuizResponseDto findById(@PathVariable final Long quizId) {
    return quizService.findById(quizId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public QuizResponseDto save(@Valid @RequestBody final QuizRequestDto request) {
    return quizService.save(request);
  }

  @PutMapping("/{quizId}")
  @ResponseStatus(HttpStatus.OK)
  public QuizResponseDto update(
      @PathVariable final Long quizId, @Valid @RequestBody final QuizRequestDto request) {
    return quizService.update(quizId, request);
  }

  @DeleteMapping("/{quizId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long quizId) {
    quizService.deleteById(quizId);
  }
}
