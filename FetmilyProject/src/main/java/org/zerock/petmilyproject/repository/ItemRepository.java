package org.zerock.petmilyproject.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
//    @Query("select i from Item i")
//    Page<Item> listOfItem(Pageable pageable);

    @Query("select i from Item i")
    List<Item> listOfItem();

//     카테고리로 상품 출력
//    @Query("select i from Item i where i.category = :category and i.kind = :kind")
//    List<Item> categoryListOfItem(String category, String kind);
//
//    // 브랜드별로 상품 출력
//    @Query("select i from Item i where i.brand = :category and i.kind = :kind")
//    List<Item> ListOfNewItem(String brand, String kind);

    // 새로운 상품 출력
//    @Query("select i from Item i where i.reg_date >= CURRENT_DATE - INTERVAL 14 DAY and i.kind = :kind")
//    List<Item> brandListOfItem(String kind);

    @Query("select i from Item i where i.itemName like concat('%', :keyword, '%')")
    Page<Item> listOfSearchItem(Optional<String> keyword, Pageable pageable);

    @Query("select i from Item i where i.kind = :kind")
    List<Item> ListOfItemByKind(String kind);
    @Query("select i from Item i where i.category = 'food'")
    List<Item> ListOfItemByBrand();

//
//    @Query("select i from Item i where i.itemName like concat('%', :keyword, '%')")
//    List<Item> listOfSearchItem(Optional<String> keyword, Pageable pageable);
}
