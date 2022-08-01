package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsResPonseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestsDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
       return id;
    }

    public PostsResPonseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 개시글이 없습니다. id = " + id));
        return new PostsResPonseDto(entity);
    }
}
