package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository<Entity Class, PK>를 상속하면 기본적인 CRUD가 자동으로 생성
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findByAllDesc();
}
