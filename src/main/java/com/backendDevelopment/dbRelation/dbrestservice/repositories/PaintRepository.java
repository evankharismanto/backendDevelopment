package com.backendDevelopment.dbRelation.dbrestservice.repositories;

import com.backendDevelopment.dbRelation.dbrestservice.objects.Paint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintRepository extends JpaRepository<Paint, Integer> {
}
