package com.mugenminds.mugenminds.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private long id;
    @NotNull(message = "For 'name', enter a valid name")
    private String name;
    @NotNull(message = "'description' cannot be left empty")
    private String description;
}
