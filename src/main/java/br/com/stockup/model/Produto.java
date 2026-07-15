package br.com.stockup.model;

import br.com.stockup.enums.ModeloProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String referencia;

    @Enumerated(EnumType.STRING)
    private ModeloProduto modelo;

    @Column(nullable = false)
    private String cor;

    @Column(length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<EstoqueProduto> estoques;
}
