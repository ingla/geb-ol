package com.example.gebol.service;

import org.springframework.stereotype.Component;

@Component("pointCalculationService")
// Map place to points
public class PointCalculationService {
    public int getPointsForPlace(int place) {
        switch (place) {
            case 1: return 12;
            case 2: return 7;
            case 3: return 4;
            case 4: return 2;
            case 5: return 1;
            case 6: return 1;
            case 7: return 1;
            case 8: return 1;
            default: return 0;
        }
    }
}
