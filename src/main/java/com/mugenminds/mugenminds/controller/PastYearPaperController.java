package com.mugenminds.mugenminds.controller;

import com.mugenminds.mugenminds.payload.PastYearPaperDTO;
import com.mugenminds.mugenminds.service.PastYearPaperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/past-year-papers")
public class PastYearPaperController {

    private final PastYearPaperService pastYearPaperService;

    public PastYearPaperController(PastYearPaperService pastYearPaperService) {
        this.pastYearPaperService = pastYearPaperService;
    }

    // Create a new past year paper
    @PostMapping
    public ResponseEntity<PastYearPaperDTO> createPastYearPaper(@Valid @RequestBody PastYearPaperDTO pastYearPaperDTO) {
        PastYearPaperDTO createdPastYearPaper = pastYearPaperService.createPastYearPaper(pastYearPaperDTO);
        return new ResponseEntity<>(createdPastYearPaper, HttpStatus.CREATED);
    }

    // Update an existing past year paper
    @PutMapping("/{paperId}")
    public ResponseEntity<PastYearPaperDTO> updatePastYearPaper(@PathVariable Long paperId,
                                                                @Valid @RequestBody PastYearPaperDTO pastYearPaperDTO) {
        PastYearPaperDTO updatedPastYearPaper = pastYearPaperService.updatePastYearPaper(paperId, pastYearPaperDTO);
        return ResponseEntity.ok(updatedPastYearPaper);
    }

    // Get a single past year paper by ID
    @GetMapping("/{paperId}")
    public ResponseEntity<PastYearPaperDTO> getPastYearPaperById(@PathVariable Long paperId) {
        PastYearPaperDTO pastYearPaperDTO = pastYearPaperService.findPastYearPaperById(paperId);
        return ResponseEntity.ok(pastYearPaperDTO);
    }

    // Get all past year papers for a specific subject
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<PastYearPaperDTO>> getPapersBySubjectId(@PathVariable Long subjectId) {
        List<PastYearPaperDTO> papers = pastYearPaperService.findAllPapersBySubjectId(subjectId);
        return ResponseEntity.ok(papers);
    }

    // Delete a past year paper by ID
    @DeleteMapping("/{paperId}")
    public ResponseEntity<String> deletePastYearPaper(@PathVariable Long paperId) {
        String responseMessage = pastYearPaperService.deletePastYearPaperById(paperId);
        return ResponseEntity.ok(responseMessage);
    }

    // Get all past year papers
    @GetMapping
    public ResponseEntity<List<PastYearPaperDTO>> getAllPapers() {
        List<PastYearPaperDTO> papers = pastYearPaperService.findAllPapers();
        return ResponseEntity.ok(papers);
    }
}
