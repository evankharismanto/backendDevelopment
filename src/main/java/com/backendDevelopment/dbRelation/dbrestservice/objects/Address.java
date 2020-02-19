package com.backendDevelopment.dbRelation.dbrestservice.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "m_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    @NonNull String line1;

    String line2;

    String line3;

    @JsonIgnore
    @OneToOne(mappedBy = "address",orphanRemoval = true)
    Customer customer;
}
