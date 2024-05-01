package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Member;

public interface LogRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email=:email and m.password=:password")
    Member login(String email, String password);
}