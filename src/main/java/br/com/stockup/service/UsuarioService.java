package br.com.stockup.service;

import br.com.stockup.dto.*;

public interface UsuarioService {

    void cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO);

    public LoginResponseDTO login(LoginUsuarioDTO loginUsuarioDTO);

    void redefinirSenha(RedefinirSenhaDTO redefinirSenhaDTO);

    void validarCodigo(ValidarCodigoDTO validarCodigoDTO);

    void novaSenha(NovaSenhaDTO novaSenhaDTO);
}
