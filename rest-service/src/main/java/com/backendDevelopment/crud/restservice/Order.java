package com.backendDevelopment.crud.restservice;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Order {
    @NonNull int id;
    @NonNull Date date;
    @NonNull int delivery;
    Address address;
    @NonNull Customer customer;
    @NonNull List<Item> item;
}
