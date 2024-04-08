package com.mugenminds.mugenminds.controller;

import com.mugenminds.mugenminds.payload.SubjectDTO;
import com.mugenminds.mugenminds.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Create a new subject
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO createdSubject = subjectService.createSubject(subjectDTO);

        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    // Get all subjects
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    // Get a single subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("id") long subjectId) {
        SubjectDTO subjectDTO = subjectService.getSubjectById(subjectId);

        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    // Update a subject by ID
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@Valid @PathVariable("id") long subjectId, @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO updatedSubject = subjectService.updateSubject(subjectDTO, subjectId);

        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    // Delete a subject by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") long subjectId) {
        String responseMessage = subjectService.deleteSubject(subjectId);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
