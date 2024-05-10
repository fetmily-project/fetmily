package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private String name;
    private String socialNumber;
    private String phone;
    private String addr;
    @NotEmpty
    private String nickname;

}
