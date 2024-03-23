package com.mugenminds.mugenminds.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "subject")
    private Set<PastYearPaper> pastYearPapers;

    @OneToMany(mappedBy = "subject")
    private Set<Note> notes;

    @OneToMany(mappedBy = "subject")
    private Set<Quiz> quizzes;
}
