package edu.kpi.iasa.mmsa.petshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtRequestDto {
    String username;
    String password;

}
