package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
