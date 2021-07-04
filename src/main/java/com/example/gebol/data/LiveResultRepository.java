package com.example.gebol.data;

import com.example.gebol.model.LiveResult;
import com.example.gebol.model.Result;

import java.util.List;

public interface LiveResultRepository {
    LiveResult save(LiveResult liveResult);
    List<LiveResult> saveAll(List<LiveResult> liveResults);
    List<LiveResult> findByDisciplineId(Long disciplineId);
}
