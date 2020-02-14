package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__({@ConstructorProperties({"orders"})}))
@Accessors(fluent=true) @Setter @Getter
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
public class Transactions{
    @NonNull List<Order> orders;
}
