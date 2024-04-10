package com.mugenminds.mugenminds.service.impl;

import com.mugenminds.mugenminds.model.PastYearPaper;
import com.mugenminds.mugenminds.model.Subject;
import com.mugenminds.mugenminds.payload.PastYearPaperDTO;
import com.mugenminds.mugenminds.repository.PastYearRepository;
import com.mugenminds.mugenminds.repository.SubjectRepository;
import com.mugenminds.mugenminds.service.PastYearPaperService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PastYearPaperServiceImpl implements PastYearPaperService {

    private final PastYearRepository pastYearRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public PastYearPaperServiceImpl(PastYearRepository pastYearRepository,
                                    SubjectRepository subjectRepository,
                                    ModelMapper modelMapper) {
        this.pastYearRepository = pastYearRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PastYearPaperDTO createPastYearPaper(PastYearPaperDTO pastYearPaperDTO) {
        // Validate subject exists
        Subject subject = subjectRepository.findById(pastYearPaperDTO.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + pastYearPaperDTO.getSubjectId()));

        PastYearPaper paper = modelMapper.map(pastYearPaperDTO, PastYearPaper.class);
        paper.setSubject(subject); // Set the subject to the paper
        PastYearPaper savedPaper = pastYearRepository.save(paper);

        return modelMapper.map(savedPaper, PastYearPaperDTO.class);
    }

    @Override
    @Transactional
    public PastYearPaperDTO updatePastYearPaper(Long paperId, PastYearPaperDTO pastYearPaperDTO) {
        PastYearPaper paper = pastYearRepository.findById(paperId)
                .orElseThrow(() -> new EntityNotFoundException("Past year paper not found with id: " + paperId));

        // Optional: Validate subject exists if subjectId is being updated

        modelMapper.map(pastYearPaperDTO, paper);
        PastYearPaper updatedPaper = pastYearRepository.save(paper);

        return modelMapper.map(updatedPaper, PastYearPaperDTO.class);
    }

    @Override
    public PastYearPaperDTO findPastYearPaperById(Long paperId) {
        PastYearPaper paper = pastYearRepository.findById(paperId)
                .orElseThrow(() -> new EntityNotFoundException("Past year paper not found with id: " + paperId));

        return modelMapper.map(paper, PastYearPaperDTO.class);
    }

    @Override
    public List<PastYearPaperDTO> findAllPapersBySubjectId(Long subjectId) {
        List<PastYearPaper> papers = pastYearRepository.findBySubjectId(subjectId);

        return papers.stream()
                .map(paper -> modelMapper.map(paper, PastYearPaperDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String deletePastYearPaperById(Long paperId) {
        if (!pastYearRepository.existsById(paperId)) {
            throw new EntityNotFoundException("Past year paper not found with id: " + paperId);
        }
        pastYearRepository.deleteById(paperId);

        return "Past year paper successfully deleted";
    }

    @Override
    public List<PastYearPaperDTO> findAllPapers() {
        List<PastYearPaper> papers = pastYearRepository.findAll();

        return papers.stream()
                .map(paper -> modelMapper.map(paper, PastYearPaperDTO.class))
                .collect(Collectors.toList());
    }
}