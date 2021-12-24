package edu.kpi.iasa.mmsa.petshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Data
public class AccountDto {
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private String password;

    private Long roleId=1L;

    @NotNull
    private String email;

    private Long shelterId = 1L;
}
