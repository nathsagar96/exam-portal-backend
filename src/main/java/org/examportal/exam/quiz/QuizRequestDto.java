package org.examportal.exam.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuizRequestDto(
    @NotBlank(message = "Title cannot be blank") String title,
    @NotBlank(message = "Description cannot be blank") String description,
    @NotNull(message = "Max marks cannot be null") int maxMarks,
    @NotNull(message = "Number of questions cannot be null") int numberOfQuestions,
    boolean active,
    @NotNull(message = "Category id cannot be null") Long categoryId) {}
