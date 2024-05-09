package org.zerock.petmilyproject.repository.BoardSearch;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.petmilyproject.domain.*;
import org.zerock.petmilyproject.dto.BoardImageDTO;
import org.zerock.petmilyproject.dto.BoardListAllDTO;
import org.zerock.petmilyproject.dto.BoardListReplyCountDTO;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.title.contains("11"));

        booleanBuilder.or(board.content.contains("11"));

        query.where(booleanBuilder);
        query.where(board.boardId.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.boardId.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                             String keyword,
                                                             Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board);

        if( (types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                        case "c":
                            booleanBuilder.or(board.content.contains(keyword));
                            break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.boardId.gt(0L));

        JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.boardId,
                board.title,
                board.member,
                board.regDate,
                reply.count().as("replyCount")
        ));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }

//    @Override
//    public Page<BoardListReplyCountDTO> searchWithAll(String[] types,
//                                                      String keyword,
//                                                      Pageable pageable) {
//
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//
//        JPQLQuery<Board> boardJPQLQuery = from(board);
//        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));
//
//        getQuerydsl().applyPagination(pageable, boardJPQLQuery);
//
//        List<Board> boardList = boardJPQLQuery.fetch();
//
//        boardList.forEach(board1 -> {
//            System.out.println(board1.getBoardId());
//            System.out.println(board1.getImageSet());
//            System.out.println("----------------");
//        });
//
//        return null;
//    }

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;    // 회원정보를 가져오기 위해 추가

        JPQLQuery<Board> boardJPQLQuery = from(board);
        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board)); //left join
        boardJPQLQuery.leftJoin(board.member, member);  // 회원 정보 가져오기

        if( (types != null && types.length > 0) && keyword != null ){

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }//end for
            boardJPQLQuery.where(booleanBuilder);
        }

        boardJPQLQuery.groupBy(board);

        getQuerydsl().applyPagination(pageable, boardJPQLQuery); //paging



        JPQLQuery<Tuple> tupleJPQLQuery = boardJPQLQuery.select(board, reply.countDistinct(), member);

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {

            Board board1 = (Board) tuple.get(board);
            Member member1 = (Member) tuple.get(member);

            long replyCount = tuple.get(1,Long.class);

            BoardListAllDTO dto = BoardListAllDTO.builder()
                    .boardId(board1.getBoardId())
                    .memberId(member1.getMemberId())
                    .nickname(member1.getNickname())
                    .title(board1.getTitle())
                    .regDate(board1.getRegDate())
                    .replyCount(replyCount)
                    .build();

            List<BoardImageDTO> imageDTOS = board1.getImageSet().stream().sorted()
                    .map(boardImage -> BoardImageDTO.builder()
                            .uuid(boardImage.getUuid())
                            .fileName(boardImage.getFileName())
                            .ord(boardImage.getOrd())
                            .build()
                    ).collect(Collectors.toList());

            dto.setBoardImages(imageDTOS);

            return dto;
        }).collect(Collectors.toList());

        long totalCount = boardJPQLQuery.fetchCount();


        return new PageImpl<>(dtoList, pageable, totalCount);
    }


}
