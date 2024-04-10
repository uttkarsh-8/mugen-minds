package com.mugenminds.mugenminds.controller;

import com.mugenminds.mugenminds.payload.QuizDTO;
import com.mugenminds.mugenminds.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Create a new quiz
    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody QuizDTO quizDTO) {
        QuizDTO createdQuiz = quizService.createQuiz(quizDTO);

        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    // Update an existing quiz
    @PutMapping("/{id}")
    public ResponseEntity<QuizDTO> updateQuiz(@PathVariable("id") long id, @Valid @RequestBody QuizDTO quizDTO) {
        QuizDTO updatedQuiz = quizService.updateQuiz(id, quizDTO);

        return ResponseEntity.ok(updatedQuiz);
    }

    // Get a single quiz by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable("id") long id) {
        QuizDTO quizDTO = quizService.findQuizById(id);

        return ResponseEntity.ok(quizDTO);
    }

    // Get all quizzes for a specific subject
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<QuizDTO>> getQuizzesBySubjectId(@PathVariable("subjectId") long subjectId) {
        List<QuizDTO> quizzes = quizService.findAllQuizzesBySubjectId(subjectId);

        return ResponseEntity.ok(quizzes);
    }

    // Delete a quiz by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") long id) {
        String responseMessage = quizService.deleteQuizById(id);

        return ResponseEntity.ok(responseMessage);
    }

    // Get all quizzes
    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.findAllQuizzes();

        return ResponseEntity.ok(quizzes);
    }
}
