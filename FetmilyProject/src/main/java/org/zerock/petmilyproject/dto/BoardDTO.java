package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private String category;
    private Long viewCnt;
    private Long likeCnt;


}
