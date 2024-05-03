package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String socialNumber;
    private String phone;
    private String addr;
    private String nickname;

}
