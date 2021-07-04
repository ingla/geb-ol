package com.example.gebol.data;

import com.example.gebol.model.persistent.Participant;

import java.util.List;

public interface ParticipantRepository {
    List<Participant> findAll();
    Participant findOne(Long id);
    Boolean hasParticipant(String name);
    Boolean hasParticipant(Long id);
    Participant save(Participant participant);
    String getNameById(Long id);
    void deleteById(Long id);
}
