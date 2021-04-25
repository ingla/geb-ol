package com.example.gebol.data;

import com.example.gebol.model.Participant;

public interface ParticipantRepository {
    Iterable<Participant> findAll();
    Participant findOne(String id);
    Boolean hasParticipant(String name);
    Participant save(Participant participant);
}
