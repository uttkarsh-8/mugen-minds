package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.PastYearPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastYearRepository extends JpaRepository<PastYearPaper, Long> {
}
