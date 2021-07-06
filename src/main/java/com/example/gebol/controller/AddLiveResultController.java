package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.LiveResultRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.model.AddLiveResultUserInput;
import com.example.gebol.model.LiveResultListCreation;
import com.example.gebol.model.persistent.Discipline;
import com.example.gebol.model.persistent.LiveResult;
import com.example.gebol.model.persistent.Participant;
import com.example.gebol.service.LiveResultsCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class AddLiveResultController {

    private final LiveResultRepository liveResultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public AddLiveResultController(LiveResultRepository liveResultRepository,
                                   ParticipantRepository participantRepository,
                                   DisciplineRepository disciplineRepository) {
        this.liveResultRepository = liveResultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping("/init-add-live-result/choose-count")
    public String addLiveResultChooseCount(Model model) {
        log.info("addLiveResult");
        model.addAttribute("allDisciplines", getDisciplinesWithoutResult());
        model.addAttribute("addLiveResultUserInput", new AddLiveResultUserInput());
        return "init-add-live-result-choose-count";
    }

    @PostMapping("/init-add-live-result/choose-count")
    public String processAddResultChooseCount(Model model, @Valid @ModelAttribute AddLiveResultUserInput addLiveResultUserInput, BindingResult result) {
        log.info("post choose count, addliveresult: " + addLiveResultUserInput);

        if (result.hasErrors()) {
            log.info("Error");
            model.addAttribute("allDisciplines", getDisciplinesWithoutResult());
            return "init-add-live-result-choose-count";
        }

        model.addAttribute("addLiveResultUserInput", addLiveResultUserInput);

        // Get valid levels based on participantCount
        int participantCount = addLiveResultUserInput.getParticipantCount();
        List<Integer> validLevels = LiveResultsCalculationService.getValidLevels(participantCount);
        log.info("Calculated valid levels: " + validLevels);
        model.addAttribute("validLevels", validLevels);

        Discipline d = disciplineRepository.findById(addLiveResultUserInput.getDisciplineId());
        model.addAttribute("discipline", d);

        // Store empty slots for all levels in database
        saveEmptySlotsIfNotInDB(d, participantCount);

        return "init-add-live-result-choose-level";
    }

    @PostMapping("/init-add-live-result/choose-level")
    public String processAddResultChooseLevel(Model model, AddLiveResultUserInput addLiveResultUserInput) {
        log.info("post choose level, addliveresult: " + addLiveResultUserInput);

        Iterable<Participant> allParticipants = participantRepository.findAll();
        model.addAttribute("allParticipants", allParticipants);

        LiveResultListCreation resultList = new LiveResultListCreation();

        int level = addLiveResultUserInput.getLevel();
        int participantCount = addLiveResultUserInput.getParticipantCount();
        int leftoverCount = LiveResultsCalculationService.getLeftoverCount(level, participantCount);
        double placesCount = LiveResultsCalculationService.getSlotCountForLevel(level);

        if (leftoverCount > 0) {
            List<Integer> placesToBeFilled = LiveResultsCalculationService.getPlacesToBeFilled(
                    level,
                    participantCount,
                    leftoverCount
            );

            for (int i = 0; i < placesCount; i++) {
                if (placesToBeFilled.contains(i)) {
                    LiveResult r = new LiveResult();
                    r.setPlace(i);
                    r.setDisciplineId(addLiveResultUserInput.getDisciplineId());
                    r.setLevel(addLiveResultUserInput.getLevel());
                    resultList.addResult(r);
                }
            }
        } else {

            for (int i = 0; i < placesCount; i++) {
                LiveResult r = new LiveResult();
                r.setPlace(i);
                r.setDisciplineId(addLiveResultUserInput.getDisciplineId());
                r.setLevel(addLiveResultUserInput.getLevel());
                resultList.addResult(r);
            }
        }

        model.addAttribute("liveResultListCreation", resultList);
        Discipline d = disciplineRepository.findById(addLiveResultUserInput.getDisciplineId());
        model.addAttribute("discipline", d);
        return "add-live-results-for-discipline";
    }

    @PostMapping("/save-live-results")
    public String processAddResult(Model model, LiveResultListCreation liveResultListCreation, BindingResult bindingResult) {
        log.info("save-live-results");
        log.info(liveResultListCreation.getResults().toString());

        // Validate input - all participants in a level should be unique
        Set<Long> uniqueParticipants = new HashSet<>();
        List<LiveResult> results = liveResultListCreation.getResults();
        for (LiveResult res : results) {
            uniqueParticipants.add(res.getParticipantId());
        }
        if (uniqueParticipants.size() != results.size()) {
            ObjectError objectError = new ObjectError(
                    "globalError",
                    "Deltagere må være unike"
            );
            bindingResult.addError(objectError);

            Iterable<Participant> allParticipants = participantRepository.findAll();
            model.addAttribute("allParticipants", allParticipants);

            // TODO: Find a less ugly way of getting the discipline
            Discipline d = disciplineRepository.findById(liveResultListCreation.getResults().get(0).getDisciplineId());
            model.addAttribute("discipline", d);

            return "add-live-results-for-discipline";
        }

        liveResultRepository.updateAll(liveResultListCreation.getResults());
        return "redirect:/admin/live-results";
    }

    private Iterable<Discipline> getDisciplinesWithoutResult() {
        return disciplineRepository.findAll()
                .stream()
                .filter(discipline -> !liveResultRepository.hasDisciplineId(discipline.getId()))
                .filter(discipline -> discipline.isCup())
                .collect(Collectors.toList());
    }

    private void saveEmptySlotsIfNotInDB(Discipline discipline, int participantCount) {
        if (liveResultRepository.hasDisciplineId(discipline.getId())) {
            return;
        }
        List<Integer> validLevels = LiveResultsCalculationService.getValidLevels(participantCount)
                .stream().sorted()
                .collect(Collectors.toList());

        List<LiveResult> emptySlots = new ArrayList<>();

        for (int level : validLevels) {
            if (LiveResultsCalculationService.levelIsFull(level, participantCount)) {
                double placeCount = LiveResultsCalculationService.getSlotCountForLevel(level);
                for (int place = 0; place < placeCount; place++) {
                    LiveResult r = new LiveResult();
                    r.setDisciplineId(discipline.getId());
                    r.setLevel(level);
                    r.setPlace(place);
                    emptySlots.add(r);
                }
            } else {
                int leftovers = LiveResultsCalculationService.getLeftoverCount(level, participantCount);
                List<Integer> placesToBeFilled = LiveResultsCalculationService.getPlacesToBeFilled(level, participantCount, leftovers);
                for (int place : placesToBeFilled) {
                    LiveResult r = new LiveResult();
                    r.setDisciplineId(discipline.getId());
                    r.setLevel(level);
                    r.setPlace(place);
                    emptySlots.add(r);
                }
            }
        }
        liveResultRepository.saveAll(emptySlots);
        log.info("Saving empty slots to database for discipline " + disciplineRepository.getNameById(discipline.getId()));
        for (LiveResult r : emptySlots) {
            log.info(r.toString());
        }
    }
}
