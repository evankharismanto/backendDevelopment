package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"color", "type","litre"})}))
@Accessors(fluent=true) @Setter @Getter
public class Paint {
    @NonNull String color;
    @NonNull String type;
    @NonNull Integer litre;
}
