package org.zerock.petmilyproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {

    private Long boardId;

    private String title;

    private String memberId;

    private LocalDateTime regDate;

    private Long replyCount;
}
