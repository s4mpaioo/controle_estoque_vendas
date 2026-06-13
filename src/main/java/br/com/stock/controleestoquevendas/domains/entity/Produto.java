package br.com.stock.controleestoquevendas.domains.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Setter
@Getter

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar id automaticamente sequencial
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;
}
