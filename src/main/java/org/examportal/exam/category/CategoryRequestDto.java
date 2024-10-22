package org.examportal.exam.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
    @NotBlank(message = "Title cannot be blank") String title,
    @NotBlank(message = "Description cannot be blank") String description) {}
