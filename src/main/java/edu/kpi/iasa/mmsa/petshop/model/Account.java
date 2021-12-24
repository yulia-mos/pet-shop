package edu.kpi.iasa.mmsa.petshop.model;

import edu.kpi.iasa.mmsa.petshop.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    @Builder.Default
    private Role role=Role.defaultRole;

    @NotNull
    @Column(unique = true)
    private String email;


    @Column(name = "reg_date")
    @Builder.Default
    private final Date regDate = new Date(System.currentTimeMillis());


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shelter_id")
    @Builder.Default
    private Shelter shelter = Shelter.defaultShelter;

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }



}
