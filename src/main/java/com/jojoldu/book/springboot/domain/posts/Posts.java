package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타내는 어노테이션, 기본값으로 클래스의 카멜케이스 이름을 _로 테이블 이름을 매칭
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK의 생성규칙을 나타냄, IDENTITY옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) // 기본 값 이외에 변경이 필요한 옵션이 있으면 사용한다. 길이가 500 이고 null값 허용하지 않음
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입 TEXT 값으로 변경
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 , 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title,String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
