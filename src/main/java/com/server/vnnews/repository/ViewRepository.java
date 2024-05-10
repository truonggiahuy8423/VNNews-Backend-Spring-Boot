package com.server.vnnews.repository;

import com.server.vnnews.entity.View;
import com.server.vnnews.entity.composite.ViewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, ViewId> {
}
