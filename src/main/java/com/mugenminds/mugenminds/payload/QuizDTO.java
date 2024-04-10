package com.mugenminds.mugenminds.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private long id;
    @NotNull(message = "cannot be empty!!")
    private Long subjectId;
    @NotBlank(message = "cannot be empty!!")
    private String title;
    @NotBlank(message = "cannot be empty!!")
    private String googleDriveLink;

}
