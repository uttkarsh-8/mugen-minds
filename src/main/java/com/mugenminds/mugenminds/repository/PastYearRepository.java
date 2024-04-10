package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.PastYearPaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastYearRepository extends JpaRepository<PastYearPaper, Long> {
    List<PastYearPaper> findBySubjectId(Long subjectId);
}
