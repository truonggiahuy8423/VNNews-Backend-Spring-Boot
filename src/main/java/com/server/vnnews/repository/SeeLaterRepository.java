package com.server.vnnews.repository;

import com.server.vnnews.entity.SeeLater;
import com.server.vnnews.entity.composite.SeeLaterId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeeLaterRepository extends JpaRepository<SeeLater, SeeLaterId> {
}
