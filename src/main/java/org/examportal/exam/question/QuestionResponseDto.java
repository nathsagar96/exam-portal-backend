package org.examportal.exam.question;

import java.util.List;

public record QuestionResponseDto(
        Long id, String title, List<String> options, String correctAnswer, Long quizId) {}
