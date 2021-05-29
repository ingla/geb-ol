package com.example.gebol.data;

import com.example.gebol.model.Participant;

import java.util.List;

public interface ParticipantRepository {
    List<Participant> findAll();
    Participant findOne(Long id);
    Boolean hasParticipant(String name);
    Participant save(Participant participant);
    String getNameById(Long id);
}
