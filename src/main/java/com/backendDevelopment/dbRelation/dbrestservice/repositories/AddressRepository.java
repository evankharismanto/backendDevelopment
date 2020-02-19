package com.backendDevelopment.dbRelation.dbrestservice.repositories;

import com.backendDevelopment.dbRelation.dbrestservice.objects.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
