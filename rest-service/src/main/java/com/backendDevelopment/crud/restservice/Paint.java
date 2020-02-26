package com.backendDevelopment.crud.restservice;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Paint {
    @NonNull String color;
    @NonNull String type;
    @NonNull Integer litre;
}
