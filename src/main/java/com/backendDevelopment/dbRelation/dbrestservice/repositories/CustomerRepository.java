package com.backendDevelopment.dbRelation.dbrestservice.repositories;

import com.backendDevelopment.dbRelation.dbrestservice.objects.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
