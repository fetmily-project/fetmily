package org.zerock.petmilyproject.domain;


import lombok.*;
import org.zerock.petmilyproject.dto.PetDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(length = 500, nullable = false)
    private String petName;

    @Column(length = 500, nullable = false)
    private String petType;

    @Column(length = 500)
    private String birth;

    @Column(length = 500)
    private String weight;

    @Column(length = 500)
    private String neut;

    @Column(length = 500)
    private String sex;

    @Column(length = 1000)
    private String etc;

    @Column(length = 1000)
    private String petImage;

    public void changePet(PetDTO petDTO){
        this.petId = petDTO.getPetId();
        this.petName = petDTO.getPetName();
        this.petType = petDTO.getPetType();
        this.birth = petDTO.getBirth();
        this.weight = petDTO.getWeight();
        this.neut = petDTO.getNeut();
        this.etc = petDTO.getEtc();
        this.petImage = petDTO.getPetImage();
        this.sex = petDTO.getSex();
        this.member = Member.builder()
                .memberId(petDTO.getMemberId())
                .build();
    }
}
