package com.hyemin.book.domain.posts;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Posts,Long>
{
    @Query("SELECT p FROM Posts  p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    //<>안에는 Entity 클래스와 PK타입이 들어간다. 그럼 CRUD메소드 자동 생성된다.
}
