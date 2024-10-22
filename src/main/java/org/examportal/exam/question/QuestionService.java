package org.examportal.exam.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.examportal.exam.quiz.QuizRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final QuestionMapper questionMapper;

  public QuestionResponseDto save(QuestionRequestDto request) {
    Question question = questionMapper.toEntity(request);

    if (quizRepository.findById(request.quizId()).isEmpty()) {
      throw new IllegalStateException("Quiz not found with id " + request.quizId());
    }

    Question savedQuestion = questionRepository.save(question);

    return questionMapper.toDto(savedQuestion);
  }

  public List<QuestionResponseDto> findAll() {
    return questionRepository.findAll().stream().map(questionMapper::toDto).toList();
  }

  public List<QuestionResponseDto> findAllByQuizId(Long quizId) {

    if (quizRepository.findById(quizId).isEmpty()) {
      throw new IllegalStateException("Quiz not found with id " + quizId);
    }

    return questionRepository.findAllByQuizId(quizId).stream().map(questionMapper::toDto).toList();
  }

  public QuestionResponseDto findById(Long id) {
    Question question =
        questionRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Question not found with id " + id));

    return questionMapper.toDto(question);
  }

  public QuestionResponseDto update(Long id, QuestionRequestDto request) {
    Question question =
        questionRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Question not found with id " + id));

    question.setTitle(request.title());
    question.setOptions(request.options());
    question.setCorrectAnswer(request.correctAnswer());

    if (quizRepository.findById(request.quizId()).isEmpty()) {
      throw new IllegalStateException("Quiz not found with id " + request.quizId());
    }
    question.setQuizId(request.quizId());

    Question updatedQuestion = questionRepository.save(question);
    return questionMapper.toDto(updatedQuestion);
  }

  public void deleteById(Long id) {
    questionRepository
        .findById(id)
        .orElseThrow(() -> new IllegalStateException("Question not found with id " + id));
    questionRepository.deleteById(id);
  }
}
