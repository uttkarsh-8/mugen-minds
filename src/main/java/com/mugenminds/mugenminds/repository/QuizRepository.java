package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findBySubjectId(Long subjectId);
}
