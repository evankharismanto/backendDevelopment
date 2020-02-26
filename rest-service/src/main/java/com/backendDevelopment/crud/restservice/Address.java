package com.backendDevelopment.crud.restservice;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Address {
    @NonNull String line1;
    String line2;
    String line3;
}
