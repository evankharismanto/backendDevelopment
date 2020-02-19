package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Order {
    @NonNull int id;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NonNull Date date;
    @NonNull int delivery;
    Address address;
    @NonNull Customer customer;
    @NonNull List<Item> item;
}
