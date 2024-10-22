package org.examportal.exam.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quizzes")
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String title;

  private String description;

  @Builder.Default
  @Column(nullable = false)
  private int maxMarks = 0;

  @Builder.Default
  @Column(nullable = false)
  private int numberOfQuestions = 0;

  @Builder.Default
  @Column(nullable = false)
  private boolean active = false;

  @Column(nullable = false)
  private Long categoryId;
}
