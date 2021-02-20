package com.example.gebol.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Discipline {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    @NotNull
    private boolean isCup;
}
