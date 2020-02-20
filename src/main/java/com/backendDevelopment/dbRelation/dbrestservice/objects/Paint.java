package com.backendDevelopment.dbRelation.dbrestservice.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "m_paint")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    @NonNull String color;

    @NonNull String type;

    @NonNull Integer litre;

    @JsonIgnore
    @OneToOne(mappedBy = "paint")
    Item item;
}
