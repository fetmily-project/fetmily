package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findByPetId(Long petId);

    @Query("select p from Pet p where p.member.memberId=:memberId")
    List<Pet> searchAll(Long memberId);
}
