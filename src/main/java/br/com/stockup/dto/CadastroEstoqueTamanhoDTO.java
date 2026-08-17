package br.com.stockup.dto;

import br.com.stockup.enums.Tamanho;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroEstoqueTamanhoDTO {

    @NotNull
    private Tamanho tamanho;

    @NotNull
    @Min(0)
    private Integer quantidade;
}