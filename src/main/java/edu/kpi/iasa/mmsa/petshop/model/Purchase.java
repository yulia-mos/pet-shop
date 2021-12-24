package edu.kpi.iasa.mmsa.petshop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "purchases")
@Getter
public class Purchase
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_id")
    private Account buyer;
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Setter
    @Column(name = "date_time")
    @Builder.Default
    private Date dateTime=new Date(System.currentTimeMillis());
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    @Builder.Default
    private Status status=Status.defaultStatus;

}
