package br.com.stockup.dto;

import br.com.stockup.enums.Ficha;
import br.com.stockup.enums.TipoEstoque;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CadastroEstoqueAtacado {
    @NotNull
    private Long produtoId;

    @NotNull
    private Ficha ficha;

    @NotNull
    @Min(0)
    private Integer quantidadeFichas;

    @NotNull
    private BigDecimal precoCusto;

    @NotNull
    private BigDecimal precoVenda;
}
