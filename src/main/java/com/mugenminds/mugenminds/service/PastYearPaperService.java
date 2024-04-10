package com.mugenminds.mugenminds.service;

import com.mugenminds.mugenminds.payload.PastYearPaperDTO;

import java.util.List;

public interface PastYearPaperService {
    PastYearPaperDTO createPastYearPaper(PastYearPaperDTO pastYearPaperDTO);
    PastYearPaperDTO updatePastYearPaper(Long paperId, PastYearPaperDTO pastYearPaperDTO);
    PastYearPaperDTO findPastYearPaperById(Long paperId);
    List<PastYearPaperDTO> findAllPapersBySubjectId(Long subjectId);
    String deletePastYearPaperById(Long paperId);
    List<PastYearPaperDTO> findAllPapers();
}
