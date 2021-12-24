package edu.kpi.iasa.mmsa.petshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@NoArgsConstructor
@Data
public class PurchaseDto {
    @NotNull
    private Long buyerId;

    @NotNull
    private Long petId;

    private Date dateTime=new Date(System.currentTimeMillis());

    @NotNull
    private Long statusId;
}
