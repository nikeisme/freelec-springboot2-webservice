package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 Database를 접근하게 해줄 jpaRepository를 생성한다.
// 인터페이스 생성 후, JpaRepository를 상송하면 기본적인 CRUD 메소드가 자동으로 생성
// entity 클래스는 기본 Repository와 함께 움직여야해서 도메인 패키지에서 함께 관리해야함.
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
