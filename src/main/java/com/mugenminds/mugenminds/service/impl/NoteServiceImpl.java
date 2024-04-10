package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.Note;
import com.mugenminds.mugenminds.model.Subject;
import com.mugenminds.mugenminds.payload.NoteDTO;
import com.mugenminds.mugenminds.repository.NoteRepository;
import com.mugenminds.mugenminds.repository.SubjectRepository;
import com.mugenminds.mugenminds.service.NoteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final SubjectRepository subjectRepository; // Add SubjectRepository
    private final ModelMapper modelMapper;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.subjectRepository = subjectRepository; // Initialize SubjectRepository
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public NoteDTO createNote(NoteDTO noteDTO) {
        Note note = modelMapper.map(noteDTO, Note.class);

        Subject subject = subjectRepository.findById(noteDTO.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + noteDTO.getSubjectId()));
        note.setSubject(subject);

        Note savedNote = noteRepository.save(note);

        return modelMapper.map(savedNote, NoteDTO.class);
    }

    @Override
    @Transactional
    public NoteDTO updateNote(long noteId, NoteDTO noteDTO) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + noteId));
        note.setTitle(noteDTO.getTitle());
        note.setGoogleDriveLink(noteDTO.getGoogleDriveLink());

        if (noteDTO.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(noteDTO.getSubjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + noteDTO.getSubjectId()));

            note.setSubject(subject);
        }

        Note updatedNote = noteRepository.save(note);

        return modelMapper.map(updatedNote, NoteDTO.class);
    }

    @Override
    public NoteDTO findNoteById(long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + noteId));

        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public List<NoteDTO> findAllNotesBySubjectId(long subjectId) {
        List<Note> notes = noteRepository.findBySubjectId(subjectId);

        return notes.stream()
                .map(note -> modelMapper.map(note, NoteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String deleteNote(long noteId) {
        noteRepository.deleteById(noteId);

        return "Note deleted successfully!!";
    }

    @Override
    public List<NoteDTO> findAllNotes() {
        List<Note> notes = noteRepository.findAll();

        return notes.stream()
                .map(note -> modelMapper.map(note, NoteDTO.class))
                .collect(Collectors.toList());
    }
}

