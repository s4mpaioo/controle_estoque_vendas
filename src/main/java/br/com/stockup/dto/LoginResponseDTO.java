package br.com.stockup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String nome;
    private String email;
}

