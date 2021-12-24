package edu.kpi.iasa.mmsa.petshop.dto;

import edu.kpi.iasa.mmsa.petshop.model.Account;
import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import edu.kpi.iasa.mmsa.petshop.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Blob;

@NoArgsConstructor
@Data
public class PetDto {
    private Long typeId=1L;

    private String name;

    @NotNull
    private Long sellerId;

    private String description;

    @NotNull
    @Digits(integer=10, fraction = 2)
    private BigDecimal price;

    private Long shelterId=1L;
}
