package com.mugenminds.mugenminds.payload;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "cannot be empty!!")
    private Long subjectId;
    @NotBlank(message = "cannot be empty!!")
    private String year;
    @NotBlank(message = "cannot be empty!!")
    private String googleDriveLink;
}
