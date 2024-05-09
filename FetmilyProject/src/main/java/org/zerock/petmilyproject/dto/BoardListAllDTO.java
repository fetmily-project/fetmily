package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListAllDTO {

    private Long boardId;

    @NotEmpty
    private Long memberId;

    private String nickname;

    private String title;

    private LocalDateTime regDate;

    private Long replyCount;

    private List<BoardImageDTO> boardImages;
}
