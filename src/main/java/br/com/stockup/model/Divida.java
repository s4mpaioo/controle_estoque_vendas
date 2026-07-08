package br.com.stockup.model;

import br.com.stockup.enums.PeriodicidadePagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "divida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorTotal;

    private Integer quantidadeParcelas;

    @Enumerated(EnumType.STRING)
    private PeriodicidadePagamento periodicidade;

    private Boolean quitada;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @OneToMany(mappedBy = "divida", cascade = CascadeType.ALL)
    private List<Parcela> parcelas;

}
