package com.backendDevelopment.crud.restservice;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Customer {
    @NonNull String name;
    @NonNull String phoneNumber;
    @NonNull Address address;
}
