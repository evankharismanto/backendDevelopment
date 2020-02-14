package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"name", "phoneNumber", "address"})}))
@Accessors(fluent=true) @Setter @Getter
public class Customer {
    @NonNull String name;
    @NonNull String phoneNumber;
    @NonNull Address address;
}
