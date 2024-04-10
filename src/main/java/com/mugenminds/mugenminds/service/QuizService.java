package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.QuizDTO;

import java.util.List;

public interface QuizService {
    QuizDTO createQuiz(QuizDTO quizDTO);
    QuizDTO updateQuiz(long quizId, QuizDTO quizDTO);
    QuizDTO findQuizById(long quizId);
    List<QuizDTO> findAllQuizzesBySubjectId(long subjectId);
    String deleteQuizById(long quizId);
    List<QuizDTO> findAllQuizzes();
}
