package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.exception.ResourceNotFoundException;
import com.mugenminds.mugenminds.model.Subject;
import com.mugenminds.mugenminds.payload.SubjectDTO;
import com.mugenminds.mugenminds.repository.SubjectRepository;
import com.mugenminds.mugenminds.service.SubjectService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, ModelMapper modelMapper){
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {

        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);

        return modelMapper.map(savedSubject,SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {

        List<Subject> subjects = subjectRepository.findAll();

        return subjects.stream().map((subject)-> modelMapper.map(subject, SubjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(long subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO, long subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());

        Subject savedSubject = subjectRepository.save(subject);

        return modelMapper.map(savedSubject, SubjectDTO.class);
    }

    @Override
    public String deleteSubject(long subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id"));

        subjectRepository.delete(subject);

        return "Subject deleted successfully!!";
    }
}
