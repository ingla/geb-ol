package com.example.gebol.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class Discipline {

    private final String id;
    private final String name;
    private final String location;
    private final LocalDateTime date;
    private final boolean isCup;
}
