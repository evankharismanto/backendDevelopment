package com.backendDevelopment.dbRelation.dbrestservice.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "t_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    @NonNull Integer amount;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "paint_id")
    Paint paint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Order order;
}
