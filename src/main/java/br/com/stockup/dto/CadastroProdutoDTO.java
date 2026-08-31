package br.com.stockup.dto;

import br.com.stockup.enums.ModeloProduto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroProdutoDTO {
    @NotBlank(message = "A referência é obrigatória.")
    private String referencia;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A marca é obrigatória.")
    private String marca;

    @NotNull(message = "O modelo é obrigatório.")
    private ModeloProduto modelo;

    @NotBlank(message = "A cor é obrigatória.")
    private String cor;

    @NotNull(message = "A quantidade mínima de pares é obrigatória.")
    @Min(value = 0, message = "A quantidade mínima não pode ser negativa.")
    private Integer estoqueMinimo;

    private String descricao;

    @NotNull
    private Long lojaId;
}
