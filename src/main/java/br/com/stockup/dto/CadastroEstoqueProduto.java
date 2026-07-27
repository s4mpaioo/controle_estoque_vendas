package br.com.stockup.dto;

import br.com.stockup.enums.Ficha;
import br.com.stockup.enums.TipoEstoque;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CadastroEstoqueProduto {
    private Long produtoId;
    private TipoEstoque tipoEstoque;
    private Ficha ficha;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private LocalDate dataEntrada;
}
