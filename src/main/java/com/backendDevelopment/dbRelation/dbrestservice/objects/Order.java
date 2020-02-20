package com.backendDevelopment.dbRelation.dbrestservice.objects;

import lombok.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    Date date;

    @NonNull Integer delivery;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="address_id")
    Address address;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="customer_id")
    Customer customer;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="order_id")
    List<Item> items;
}
