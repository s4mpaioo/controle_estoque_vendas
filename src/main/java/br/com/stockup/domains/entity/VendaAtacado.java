package br.com.stockup.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "item_venda_atacado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VendaAtacado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidadeFichas;

    private BigDecimal valorFicha;

    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "estoque_produto_id", nullable = false)
    private EstoqueProduto estoqueProduto;

}
