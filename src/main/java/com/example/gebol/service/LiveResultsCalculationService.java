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
        slotCount += getCountMatchesInExtraLevel(ceil, participantCount)*2;
        return slotCount;
    }

    public static Boolean levelIsFull(int level, int participantCount) {
        double log2 = Math.log(participantCount) / Math.log(2);
        Double ceil = Math.ceil(log2);
        Double floor = Math.floor(log2);

        return floor.equals(ceil) || (level < floor);
    }

    public static int getCountMatchesInExtraLevel(int level, int participantCount) {
        double log2 = Math.log(participantCount) / Math.log(2);
        double floor = Math.floor(log2);

        if (levelIsFull(level, participantCount)) {
            return 0;
        }

        int leftovers = participantCount - (int) Math.pow(2, floor);
        return leftovers;
    }

    public static List<Integer> getPlacesToBeFilled(int level, int leftoverCount) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < leftoverCount; i++) {
            int correctMatch = getNthBestPlace(level, i);

            list.add(correctMatch*2);
            list.add(correctMatch*2+1);
        }
        return list;
    }

    private static int getNthBestPlace(int level, int n) {
        if (level == 0) {
            return 0;
        }
        int half = (int) Math.pow(2,(level-1));
        if (n >= half) {
            return getOpponentPlace(getNthBestPlace(level, n - half));
        } else {
            return getBestChildBasedOnColor(level, getNthBestPlace(level-1, n));
        }
    }

    /**
     * WARNING gives wrong answer for level 0. Avoid < 4 participants
     */
    private static int getOpponentPlace(int place) { 
        
        if (place % 2 == 0) {
            return place + 1;
        }
        else {
            return place - 1;
        }
    }
    private static int getBestChildBasedOnColor(int level, int parentPlace) {

        int blacks = 0;
        for (int i = 0; i < (level + 1); i++){ // count black cells in inward-path
            if (((level - i) + (int)(parentPlace / Math.pow(2, i))) % 2 == 0){
                blacks += 1;
            }
        }
        log.info("level " + level + " place " + parentPlace + " blacks " + blacks);
        if (blacks * 2 < level + 1) {
            return 2 * parentPlace + ((level + 1) % 2);  //the black child
        }
        else if (blacks * 2 > level + 1) {
            return 2 * parentPlace + (level % 2);  //the white child
        }
        else {
            return 2 * parentPlace + ((level + parentPlace + 1) % 2);  //the opposite child
        }
    }
}
