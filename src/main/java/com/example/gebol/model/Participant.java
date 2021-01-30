package com.example.gebol.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Participant {

    private final String id;
    private final String firstName;
    private final String lastname;
}
