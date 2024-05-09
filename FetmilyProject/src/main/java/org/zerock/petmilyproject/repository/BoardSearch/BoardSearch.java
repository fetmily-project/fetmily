package org.zerock.petmilyproject.repository.BoardSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.petmilyproject.domain.Board;
import org.zerock.petmilyproject.dto.BoardListAllDTO;
import org.zerock.petmilyproject.dto.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);

//    Page<BoardListReplyCountDTO> searchWithAll(String[] types,
//                                               String keyword,
//                                               Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types,
                                        String keyword,
                                        Pageable pageable);
}
