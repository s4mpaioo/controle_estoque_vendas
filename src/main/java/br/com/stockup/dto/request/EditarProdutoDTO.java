package br.com.stockup.dto.request;

import br.com.stockup.enums.ModeloProduto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditarProdutoDTO {
    @NotBlank(message = "A referência é obrigatória.")
    private String referencia;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "A marca é obrigatória.")
    private String marca;

    @NotNull
    private ModeloProduto modelo;

    @NotBlank(message = "A cor é obrigatória.")
    private String cor;

    @NotNull(message = "A quantidade mínima de pares é obrigatória.")
    @Min(value = 0, message = "A quantidade mínima não pode ser negativa.")
    private Integer estoqueMinimo;

    private String descricao;
}
