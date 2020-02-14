package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"amount", "paint"})}))
@Accessors(fluent=true) @Setter @Getter
public class Item {
    @NonNull Integer amount;
    @NonNull Paint paint;
}
