package br.com.stockup.dto;

import br.com.stockup.enums.TipoLoja;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CadastroUsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String nomeLoja;
    private TipoLoja tipoLoja;
}
