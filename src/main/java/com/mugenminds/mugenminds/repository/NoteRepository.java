package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findBySubjectId(long subjectId);
}
