package edu.kpi.iasa.mmsa.petshop.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="statuses")
public class Status {
    public final static Status defaultStatus= new Status(1L, "Awaiting confirmation");
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;


    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
