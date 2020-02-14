package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"id", "date", "delivery", "address", "customer", "item"})}))
@Accessors(fluent=true) @Setter @Getter
public class Order {
    @NonNull int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NonNull Date date;
    @NonNull int delivery;
    Address address;
    @NonNull Customer customer;
    @NonNull List<Item> item;
}
