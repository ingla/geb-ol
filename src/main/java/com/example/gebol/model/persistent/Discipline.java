package com.example.gebol.model.persistent;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Discipline {

    public Discipline(Long id, String name, String location, LocalDateTime date, Boolean isCup) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.isCup = isCup;
    }

    private Long id;

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
