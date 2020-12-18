package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // Posts class 와 거의 유사하지만 Dto class를 추가로 생성한 이유는 Entity class를 Request/Response class로 사용하면 안되기때문.
    // 이유는 Entity class는 DB와 맞닿은 핵심 class이기 때문. Entity와 controller에서 쓸 Dto는 분리해서 사용하는게 좋음

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return  Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
    }
}
