package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Member;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
