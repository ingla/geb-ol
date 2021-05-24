package com.example.gebol.data;

import com.example.gebol.model.Discipline;

public interface DisciplineRepository {
    Iterable<Discipline> findAll();
    Discipline findOne(String name);
    String getNameById(Long id);
    Discipline save(Discipline discipline);
}
