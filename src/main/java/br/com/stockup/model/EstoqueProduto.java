package br.com.stockup.model;

import br.com.stockup.enums.Grade;
import br.com.stockup.enums.TipoEstoque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "estoque_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EstoqueProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer quantidadeTotalPares; // soma total dos pares disponíveis

    private Integer paresPorFicha;

    @Column(nullable = false)
    private BigDecimal precoCompra;

    @Column(nullable = false)
    private BigDecimal precoVenda;

    @Column(nullable = false)
    private Integer estoqueMinimo = 0;

    private LocalDateTime dataEntrada;

    @Enumerated(EnumType.STRING)
    private TipoEstoque tipoEstoque;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @OneToMany(mappedBy = "estoqueProduto", cascade = CascadeType.ALL)
    private List<TamanhoEstoque> tamanhos;
}
