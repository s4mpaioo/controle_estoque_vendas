package br.com.stockup.dto;

import br.com.stockup.enums.Ficha;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CadastroEstoqueAtacadoDTO {
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
