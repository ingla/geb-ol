package com.example.gebol.service;

import lombok.extern.slf4j.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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

    public static Boolean levelIsFull(int level, int participantCount) {
        double log2 = Math.log(participantCount) / Math.log(2);
        Double ceil = Math.ceil(log2);
        Double floor = Math.floor(log2);

        return floor.equals(ceil) || (level < floor);
    }

    public static int getLeftoverCount(int level, int participantCount) {
        double log2 = Math.log(participantCount) / Math.log(2);
        double floor = Math.floor(log2);

        if (levelIsFull(level, participantCount)) {
            return 0;
        }

        int leftovers = participantCount - (int) Math.pow(2, floor);
        return leftovers*2;
    }

    public static List<Integer> getPlacesToBeFilled(int level, int leftoverCount) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < leftoverCount/2; i++) {
            int correctMatch = table(level, i);

            list.add(correctMatch*2);
            list.add(correctMatch*2+1);
        }
        return list;
    }

    private static int table (int level, int place) {
        if (level == 0) {
            return 0;
        }
        int half = (int) Math.pow(2,(level-1));
        if (place >= half) {
            return opponent(table(level,place - half));
        } else {
            return nextOuterSlot(level, table(level-1, place));
        }
    }
    private static int opponent (int place) { // WARNING gives wrong answer for level 0. Avoid < 4 participants
        if (place % 2 == 0) {
            return place + 1;
        }
        else {
            return place - 1;
        }
    }
    private static int nextOuterSlot (int level, int place) {

        int blacks = 0;
        for (int i = 0; i < (level + 1); i++){ // count black cells in inward-path
            if (((level - i) + (int)(place / Math.pow(2, i))) % 2 == 0){
                blacks += 1;
            }
        }
        log.info("level " + level + " place " + place + " blacks " + blacks);
        if (blacks * 2 < level + 1) {
            return 2 * place + ((level + 1) % 2);  //the black child
        }
        else if (blacks * 2 > level + 1) {
            return 2 * place + (level % 2);  //the white child
        }
        else {
            return 2 * place + ((level + place  + 1) % 2);  //the opposite child
        }
    }
}
