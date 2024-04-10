package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);
    NoteDTO updateNote(long noteId, NoteDTO noteDTO);
    NoteDTO findNoteById(long id);
    String deleteNote(long id);
    List<NoteDTO> findAllNotesBySubjectId(long subjectId);
    List<NoteDTO> findAllNotes();
}
