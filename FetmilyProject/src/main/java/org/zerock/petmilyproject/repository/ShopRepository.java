package org.zerock.petmilyproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Cart;
import org.zerock.petmilyproject.domain.Item;
import org.zerock.petmilyproject.domain.Member;

public interface ShopRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i")
    Page<Item> listOfItem(Pageable pageable);
}
