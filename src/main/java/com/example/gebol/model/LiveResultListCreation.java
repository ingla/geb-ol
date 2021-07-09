package com.example.gebol.model;

import com.example.gebol.model.persistent.LiveResult;
import com.example.gebol.model.persistent.Result;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LiveResultListCreation {

    private List<LiveResult> results;
    private int participantCount;

    public LiveResultListCreation() {
        results = new ArrayList<>();
    }

    public LiveResultListCreation(List<LiveResult> results) {
        this.results = results;
    }

    public void addResult(LiveResult result) {
        results.add(result);
    }
}
