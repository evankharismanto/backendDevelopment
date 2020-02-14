package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"line1", "line2", "line3"})}))
@Accessors(fluent=true) @Setter @Getter
public class Address {
    @NonNull String line1;
    String line2;
    String line3;
}
