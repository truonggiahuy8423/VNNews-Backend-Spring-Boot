package com.server.vnnews.repository;

import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
