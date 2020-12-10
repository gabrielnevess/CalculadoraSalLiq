package com.example.calculadorasalliq.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Trabalhador implements Serializable {
    private Double salarioBruto;
    private Integer qtdDependentes;
    private Double outrosDescontos;
}
