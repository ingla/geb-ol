package com.example.gebol.model;

import com.example.gebol.model.persistent.Result;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultListCreation {

    private List<Result> results;

    public ResultListCreation() {
        results = new ArrayList<>();
    }

    public ResultListCreation(List<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        results.add(result);
    }
}
