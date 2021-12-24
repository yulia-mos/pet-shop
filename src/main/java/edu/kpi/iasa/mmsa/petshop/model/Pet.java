package edu.kpi.iasa.mmsa.petshop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Blob;
@NoArgsConstructor
@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id")
    @Builder.Default
    private Type type=Type.defaultType;

    @Setter
    private String name;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Account seller;

    @Setter
    private String description;

    @NotNull
    @Setter
    @Digits(integer=10, fraction = 2)
    private BigDecimal price;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shelter_id")
    @Builder.Default
    private Shelter shelter=Shelter.defaultShelter;

}
