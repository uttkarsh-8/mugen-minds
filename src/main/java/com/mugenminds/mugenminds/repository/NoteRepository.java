package com.mugenminds.mugenminds.repository;

import com.mugenminds.mugenminds.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
