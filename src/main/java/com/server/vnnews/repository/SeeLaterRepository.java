package com.server.vnnews.repository;

import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.SeeLater;
import com.server.vnnews.entity.composite.SeeLaterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeeLaterRepository extends JpaRepository<SeeLater, SeeLaterId> {

    @Query("SELECT s.article FROM SeeLater s")
    List<Article> findAllSeeLaterArticles();
}
