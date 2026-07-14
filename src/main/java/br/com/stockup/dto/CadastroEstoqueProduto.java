package br.com.stockup.dto;

import br.com.stockup.enums.TipoEstoque;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CadastroEstoqueProduto {
    private String cor;
    private String grade;
    private Integer quantidadeTotalPares;
    private Integer paresPorFicha;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private TipoEstoque tipoEstoque;
}
