package com.backendDevelopment.crud.restservice;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Item {
    @NonNull Integer amount;
    @NonNull Paint paint;
}
