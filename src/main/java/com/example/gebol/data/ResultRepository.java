package com.example.gebol.data;

import com.example.gebol.model.Discipline;
import com.example.gebol.model.Result;

import java.util.List;

public interface ResultRepository {
    Result save(Result result);
    List<Result> saveAll(List<Result> results);
    List<Result> findByDisciplineId(Long disciplineId);
    List<Result> findByParticipantId(Long participantId);
    List<Long> findAllDisciplineIds();
    void deleteByDisciplineId(Long id);
}
