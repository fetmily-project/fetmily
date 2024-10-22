package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Member;

import java.util.Optional;

public interface LogRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);

//    @Query("select m from Member m where m.email=:email and m.password=:password")
//    Optional<Member> login(String email, String password);
//
//    @Query("select Member from Member m, Cart c where m.memberId = :memberId")
//    Optional<Member> findByMemberCart(Long memberId);
}