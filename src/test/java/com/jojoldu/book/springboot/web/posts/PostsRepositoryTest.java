package com.jojoldu.book.springboot.web.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
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

    @After
    // JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    // 테스트 간 데이터 침범을 막기 위해 사용
    // 여러 테스트 동시에 수행 시, H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 실패 가능성 있음
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";


        // 테이블 posts에 insert/update 쿼리 실행
        // id 값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                             .title(title)
                             .content(content)
                             .author("jojoldu@gmail.com")
                             .build());

        //when
        // 테이블 posts에 있는 모든 데이터를 조회해 오는 메소드
        List<Posts> postsList = postsRepository.findAll();


        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
