package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository<Entity Class, PK>를 상속하면 기본적인 CRUD가 자동으로 생성
}
