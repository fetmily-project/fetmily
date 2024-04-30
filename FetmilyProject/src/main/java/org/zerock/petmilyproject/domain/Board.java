package org.zerock.petmilyproject.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String category;

    @Column(nullable = false)
    private Long viewCnt;

    @Column(nullable = false)
    private Long likeCnt;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

}
