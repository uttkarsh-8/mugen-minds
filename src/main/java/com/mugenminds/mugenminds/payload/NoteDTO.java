package com.mugenminds.mugenminds.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private long id;
    private long subjectId;
    private String title;
    private String content;
    private String googleDriveLink;

}
