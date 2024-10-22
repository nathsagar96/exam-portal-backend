package org.examportal.exam.quiz;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;

  public QuizResponseDto save(QuizRequestDto request) {
    Quiz quiz = quizMapper.toEntity(request);

    Quiz savedQuiz = quizRepository.save(quiz);

    return quizMapper.toDto(savedQuiz);
  }

  public List<QuizResponseDto> findAll() {
    return quizRepository.findAll().stream().map(quizMapper::toDto).toList();
  }

  public List<QuizResponseDto> findAllByCategoryId(Long categoryId) {
    return quizRepository.findAllByCategoryId(categoryId).stream().map(quizMapper::toDto).toList();
  }

  public QuizResponseDto findById(Long id) {
    Quiz quiz =
        quizRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Quiz not found with id " + id));

    return quizMapper.toDto(quiz);
  }

  public QuizResponseDto update(Long id, QuizRequestDto request) {
    Quiz quiz =
        quizRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Quiz not found with id " + id));

    quiz.setTitle(request.title());
    quiz.setDescription(request.description());
    quiz.setMaxMarks(request.maxMarks());
    quiz.setNumberOfQuestions(request.numberOfQuestions());
    quiz.setActive(request.active());
    quiz.setCategoryId(request.categoryId());

    Quiz updatedQuiz = quizRepository.save(quiz);

    return quizMapper.toDto(updatedQuiz);
  }

  public void deleteById(Long id) {
    quizRepository
        .findById(id)
        .orElseThrow(() -> new IllegalStateException("Quiz not found with id " + id));
    quizRepository.deleteById(id);
  }
}
