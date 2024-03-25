package com.mugenminds.mugenminds.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
