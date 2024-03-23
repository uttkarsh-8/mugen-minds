package com.mugenminds.mugenminds.payload;

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
    private long subjectId;
    private String title;
    private String description;
    private String googleDriveLink;

}
