package org.zerock.petmilyproject.service.pet;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.zerock.petmilyproject.domain.Member;
import org.zerock.petmilyproject.domain.Pet;
import org.zerock.petmilyproject.dto.PetDTO;
import org.zerock.petmilyproject.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final ModelMapper modelMapper;
    private final PetRepository petRepository;

    @Override
    public Long register(PetDTO petDTO) {
        Pet pet = Pet.builder()
                .petName(petDTO.getPetName())
                .petType(petDTO.getPetType())
                .birth(petDTO.getBirth())
                .neut(petDTO.getNeut())
                .sex(petDTO.getSex())
                .weight(petDTO.getWeight())
                .etc(petDTO.getEtc())
                .petImage(petDTO.getPetImage())
                .member(Member.builder()
                        .memberId(petDTO.getMemberId())
                        .build())
                .build();
        Long petId = petRepository.save(pet).getPetId();

        return petId;
    }

    @Override
    public PetDTO readOne(Long petId) {
        Pet pet = petRepository.findByPetId(petId);
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);

        return petDTO;
    }

    @Override
    public List<PetDTO> petList(Long memberId) {

        List<PetDTO> petList = petRepository.searchAllByMemberId(memberId).stream().map(
                pet -> modelMapper.map(pet, PetDTO.class)
        ).collect(Collectors.toList());
        log.info(petList);
        return petList;
    }

    @Override
    public void modify(PetDTO petDTO) {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        petRepository.save(pet);
    }

    @Override
    public void remove(Long petId) {
        petRepository.deleteById(petId);
    }
}
