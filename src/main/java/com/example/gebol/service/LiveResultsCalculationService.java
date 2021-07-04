package com.example.gebol.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiveResultsCalculationService {
    public static double getSlotCountForLevel(int level) {
        if (level == 0) {
            return 4;
        } else {
            return Math.pow(2, (level + 1));
        }
    }

    public static List<Integer> getValidLevels(int participantCount) {
        Double log2 = (Math.log(participantCount) / Math.log(2));
        int floor = (int) Math.floor(log2);
        System.out.println("log2:" + log2 + " floor: " + floor + ", partCount " + participantCount);

        int highestLevel;
        if (floor < log2) {
            highestLevel = floor;
        } else {
            highestLevel = floor - 1;
        }
        List<Integer> validLevels = new ArrayList<>();
        for (int i = highestLevel; i >= 0; i--) {
            validLevels.add(i);
        }
        return validLevels;
    }

    public static int getTotalSlotCount(int participantCount) {
        Double log2 = (Math.log(participantCount) / Math.log(2));
        int floor = (int) Math.floor(log2);

        int slotCount = 0;
        for (int level = floor; level >= 0; level--) {
            slotCount += getSlotCountForLevel(level);
        }
        int ceil = (int) Math.ceil(log2);
        slotCount += getLeftoverCount(ceil, participantCount);
        return slotCount;
    }

    public static int getLeftoverCount(int level, int participantCount) {
        Double log2 = Math.log(participantCount) / Math.log(2);
        Double ceil = Math.ceil(log2);
        Double floor = Math.floor(log2);

        if (floor.equals(ceil) || (level < floor)) {
            return 0;
        }

        int leftovers = participantCount - (int) Math.pow(2, floor);
        return leftovers*2;
    }

    public static List<Integer> getPlacesToBeFilled(int level, int participantCount, int leftoverCount) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < leftoverCount; i++) {
            list.add(i);
        }
        return list;
    }
}
