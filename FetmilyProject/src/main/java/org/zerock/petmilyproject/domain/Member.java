package org.zerock.petmilyproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.zerock.petmilyproject.dto.MemberDTO;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String password;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String socialNumber;

    @Column(length = 500)
    private String phone;

    @Column(length = 500)
    private String addr;

    @Column(length = 500)
    private String nickname;


    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<OrderItem> orderItem = new ArrayList<>();


    public void changeAddr(MemberDTO memberDTO){
        this.name = memberDTO.getName();
        this.phone = memberDTO.getPhone();
        this.addr = memberDTO.getAddr();

    }
    public void changePassword(String password){this.password = password;}
}
