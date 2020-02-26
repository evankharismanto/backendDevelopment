package com.backendDevelopment.dbRelation.dbrestservice.repositories;

import com.backendDevelopment.dbRelation.dbrestservice.objects.Order;
import org.springframework.data.jpa.repository.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
