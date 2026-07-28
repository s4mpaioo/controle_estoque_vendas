package br.com.stockup.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CadastroEstoqueVarejo {

    @NotNull
    private Long Id;

    @NotNull
    private BigDecimal precoCusto;

    @NotNull
    private BigDecimal precoVenda;

    @NotNull
    @Min(0)
    private Integer estoqueMinimo;

    private List<CadastroEstoqueTamanho> quantidadePorTamanho;
}
