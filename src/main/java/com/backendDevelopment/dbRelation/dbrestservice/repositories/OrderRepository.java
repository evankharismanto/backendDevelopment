package com.backendDevelopment.dbRelation.dbrestservice.repositories;

import com.backendDevelopment.dbRelation.dbrestservice.objects.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
