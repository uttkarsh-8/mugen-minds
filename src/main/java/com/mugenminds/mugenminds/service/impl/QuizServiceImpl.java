package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.Quiz;
import com.mugenminds.mugenminds.model.Subject;
import com.mugenminds.mugenminds.payload.QuizDTO;
import com.mugenminds.mugenminds.repository.QuizRepository;
import com.mugenminds.mugenminds.repository.SubjectRepository;
import com.mugenminds.mugenminds.service.QuizService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepository;

    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper, SubjectRepository subjectRepository) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        Subject subject = subjectRepository.findById(quizDTO.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + quizDTO.getSubjectId()));
        quiz.setSubject(subject);

        Quiz savedQuiz = quizRepository.save(quiz);

        return modelMapper.map(savedQuiz, QuizDTO.class);
    }

    @Override
    @Transactional
    public QuizDTO updateQuiz(long quizId, QuizDTO quizDTO) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        if (quizDTO.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(quizDTO.getSubjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + quizDTO.getSubjectId()));
            quiz.setSubject(subject);
        } else {
            throw new IllegalArgumentException("Subject ID must not be null");
        }

        modelMapper.map(quizDTO, quiz);
        Quiz updatedQuiz = quizRepository.save(quiz);
        return modelMapper.map(updatedQuiz, QuizDTO.class);
    }


    @Override
    public QuizDTO findQuizById(long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        return modelMapper.map(quiz, QuizDTO.class);
    }

    @Override
    public List<QuizDTO> findAllQuizzesBySubjectId(long subjectId) {
        List<Quiz> quizzes = quizRepository.findBySubjectId(subjectId);

        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String deleteQuizById(long quizId) {
        if (!quizRepository.existsById(quizId)) {
            return "Quiz not found with id: " + quizId;
        }
        quizRepository.deleteById(quizId);

        return "Quiz successfully deleted";
    }

    @Override
    public List<QuizDTO> findAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();

        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .collect(Collectors.toList());
    }
}
