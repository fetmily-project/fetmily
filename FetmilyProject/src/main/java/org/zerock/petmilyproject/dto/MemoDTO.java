package org.zerock.petmilyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoDTO {
    private Long memoId;
    private Long memberId;
    private String content;
    private LocalDateTime memoDate;
}
