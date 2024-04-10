package com.mugenminds.mugenminds.controller;

import com.mugenminds.mugenminds.payload.NoteDTO;
import com.mugenminds.mugenminds.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Create a new note
    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO createdNote = noteService.createNote(noteDTO);

        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    // Update an existing note
    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable long noteId,
                                              @Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO updatedNote = noteService.updateNote(noteId, noteDTO);

        return ResponseEntity.ok(updatedNote);
    }

    // Get a single note by ID
    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable long noteId) {
        NoteDTO noteDTO = noteService.findNoteById(noteId);

        return ResponseEntity.ok(noteDTO);
    }

    // Get all notes for a specific subject
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<NoteDTO>> getNotesBySubjectId(@PathVariable long subjectId) {
        List<NoteDTO> notes = noteService.findAllNotesBySubjectId(subjectId);

        return ResponseEntity.ok(notes);
    }

    // Delete a note by ID
    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable long noteId) {
        noteService.deleteNote(noteId);

        return ResponseEntity.ok("Note successfully deleted");
    }

    // Get all notes
    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        List<NoteDTO> notes = noteService.findAllNotes();

        return ResponseEntity.ok(notes);
    }
}
