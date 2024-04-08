package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.SubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO createSubject(SubjectDTO subjectDTO);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO getSubjectById(long subjectId);
    SubjectDTO updateSubject(SubjectDTO subjectDTO, long subjectId);
    String deleteSubject(long subjectId);
}
