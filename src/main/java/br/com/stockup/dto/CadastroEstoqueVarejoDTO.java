package br.com.stockup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CadastroEstoqueVarejoDTO {
    @NotNull
    @Positive
    private BigDecimal precoCusto;

    @NotNull
    @Positive
    private BigDecimal precoVenda;

    @NotNull
    @Min(0)
    private Integer estoqueMinimo;

    @NotNull
    private Long produtoId;

    @NotEmpty
    @Valid
    private List<CadastroEstoqueTamanhoDTO> quantidadePorTamanho;
}
