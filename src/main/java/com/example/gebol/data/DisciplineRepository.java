package com.example.gebol.data;

import com.example.gebol.model.Discipline;

public interface DisciplineRepository {
    Iterable<Discipline> findAll();
    Discipline findOne(String id);
    Discipline save(Discipline discipline);
}
