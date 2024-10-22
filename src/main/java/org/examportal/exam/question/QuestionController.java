package org.examportal.exam.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionService questionService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<QuestionResponseDto> findAll() {
    return questionService.findAll();
  }

  @GetMapping("/quiz/{quizId}")
  @ResponseStatus(HttpStatus.OK)
  public List<QuestionResponseDto> findAllByQuizId(Long quizId) {
    return questionService.findAllByQuizId(quizId);
  }

  @GetMapping("/{questionId}")
  @ResponseStatus(HttpStatus.OK)
  public QuestionResponseDto findById(Long questionId) {
    return questionService.findById(questionId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public QuestionResponseDto save(QuestionRequestDto request) {
    return questionService.save(request);
  }

  @PutMapping("/{questionId}")
  @ResponseStatus(HttpStatus.OK)
  public QuestionResponseDto update(Long questionId, QuestionRequestDto request) {
    return questionService.update(questionId, request);
  }

  @DeleteMapping("/{questionId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(Long questionId) {
    questionService.deleteById(questionId);
  }
}
