package br.com.stockup.dto;

import br.com.stockup.enums.ModeloProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroProduto {
    private String referencia;
    private String nome;
    private String marca;
    private ModeloProduto modelo;
    private String cor;
    private String descricao;
}
