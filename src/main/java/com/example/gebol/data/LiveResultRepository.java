package com.example.gebol.data;

import com.example.gebol.model.persistent.LiveResult;

import java.util.List;

public interface LiveResultRepository {
    LiveResult findOne(Long disciplineId, int bracketLevel, int place);
    LiveResult save(LiveResult liveResult);
    LiveResult update(LiveResult liveResult);
    List<LiveResult> saveAll(List<LiveResult> liveResults);
    List<LiveResult> updateAll(List<LiveResult> liveResults);
    List<LiveResult> findByDisciplineId(Long disciplineId);
    Boolean hasDisciplineId(Long disciplineId);
    List<Long> findAllDisciplineIds();
    List<LiveResult> findAllByDisciplineId(Long disciplineId);
    List<Long> findAllParticipantIdsByDisciplineId(Long disciplineId);
    void deleteByDisciplineId(Long disicplineId);
}
