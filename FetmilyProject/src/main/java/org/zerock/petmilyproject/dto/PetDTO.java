package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private Long petId;
    private Long memberId;
    private String petName;
    private String petType;
    private String birth;
    private String weight;
    private String neut;
    private String sex;
    private String etc;
}
