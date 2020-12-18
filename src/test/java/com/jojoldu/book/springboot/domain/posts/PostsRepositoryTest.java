package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public  void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void readBbs(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문" ;

        // 테이블에 insert/update 쿼리 실행. 값이 있으면 update 없으면 insert
        postsRepository.save(Posts.builder().title(title).content(content).author("rbsks147@naver.com").build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블에 모든 데터를 조회

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
