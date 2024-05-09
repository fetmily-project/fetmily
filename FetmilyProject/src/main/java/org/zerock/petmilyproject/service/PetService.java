package org.zerock.petmilyproject.service;

import org.zerock.petmilyproject.dto.PetDTO;

import java.util.List;

public interface PetService {
    Long register(PetDTO petDTO);
    PetDTO readOne(Long memberId, Long petId);
    List<PetDTO> petList(Long memberId);
    void modify(PetDTO petDTO);
    void remove(Long petId);
}
