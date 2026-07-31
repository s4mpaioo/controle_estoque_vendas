package br.com.stockup.model;

import br.com.stockup.enums.Ficha;
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

    @Column(nullable = false)
    private BigDecimal precoCusto;

    @Column(nullable = false)
    private BigDecimal precoVenda;

    @Column(nullable = false)
    private Integer estoqueMinimo = 0;

    @Enumerated(EnumType.STRING)
    private TipoEstoque tipoEstoque;

    @Enumerated(EnumType.STRING)
    private Ficha  ficha;

    private Integer quantidadeFichas;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @OneToMany(mappedBy = "estoqueProduto", cascade = CascadeType.ALL)
    private List<EstoqueTamanho> quantidadePorTamanho;
}
