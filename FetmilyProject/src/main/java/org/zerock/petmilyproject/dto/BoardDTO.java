package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private Long viewCnt;
    private Long likeCnt;


}
