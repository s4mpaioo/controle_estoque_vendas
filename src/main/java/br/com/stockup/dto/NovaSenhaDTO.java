package br.com.stockup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovaSenhaDTO {
    private String email;

    private String codigo;

    private String novaSenha;

    private String confirmarSenha;
}
