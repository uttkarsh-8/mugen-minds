package com.mugenminds.mugenminds.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PastYearPaperDTO {
    private Long id;
    private Long subjectId;
    private String year;
    private String googleDriveLink;
}
