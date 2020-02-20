package com.backendDevelopment.dbRelation.dbrestservice.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "m_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    @NonNull String name;

    @Column(name="phone_number")
    @NonNull String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "address_id")
    Address address;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    Order order;
}

