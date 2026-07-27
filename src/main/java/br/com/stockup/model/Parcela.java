package br.com.stockup.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private BigDecimal valor;

    private LocalDate dataVencimento;

    private Boolean paga;

    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "divida_id")
    private Divida divida;

}
