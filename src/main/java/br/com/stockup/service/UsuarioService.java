package br.com.stockup.service;

import br.com.stockup.dto.CadastroUsuario;
import br.com.stockup.model.Usuario;
import br.com.stockup.repository.LojaRepository;
import br.com.stockup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    //conversa com o backend
    private final UsuarioRepository usuarioRepository;

    private final LojaRepository lojaRepository;

    //para o spring entregar os repositories
    public UsuarioService(UsuarioRepository usuarioRepository, LojaRepository lojaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.lojaRepository = lojaRepository;
    }

    /*o que a service faz
    * o metodo cadastrar tem que receber o formulario que são as informações do front
    * */
    public void cadastrar(CadastroUsuario dto) {
        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
    }
}
