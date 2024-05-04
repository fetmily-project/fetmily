package org.zerock.petmilyproject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.petmilyproject.domain.Board;
import org.zerock.petmilyproject.repository.BoardSearch.BoardSearch;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.boardId =:boardId")
    Optional<Board> findByIdWithImages(Long boardId);

//    @Query(value = "select now()", nativeQuery = true)
//    String getTime();
}
