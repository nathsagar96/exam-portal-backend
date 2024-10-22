package org.examportal.exam.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record QuestionRequestDto(
    @NotBlank(message = "Question cannot be blank") String title,
    @NotNull(message = "Options cannot be null") List<String> options,
    @NotBlank(message = "Correct answer cannot be blank") String correctAnswer,
    @NotNull(message = "Quiz id cannot be null") Long quizId) {}
