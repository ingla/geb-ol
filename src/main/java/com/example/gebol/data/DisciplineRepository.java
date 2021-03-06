package com.example.gebol.data;

import com.example.gebol.model.persistent.Discipline;

import java.util.List;

public interface DisciplineRepository {
    List<Discipline> findAll();
    List<Discipline> findAllSortedByDate();
    Discipline findOne(String name);
    Discipline findById(Long id);
    String getNameById(Long id);
    Discipline save(Discipline discipline);
    void deleteById(Long id);
}
