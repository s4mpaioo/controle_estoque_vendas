package br.com.stockup.domains.entity;


import br.com.stockup.domains.entity.Cliente;
import br.com.stockup.enums.TipoLoja;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="loja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoLoja tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "loja")
    private List<br.com.stockup.domains.entity.Produto> produtos;

    @OneToMany(mappedBy = "loja")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "loja")
    private List<Venda> vendas;
}
