package br.com.stockup.service;

import br.com.stockup.dto.CadastroUsuario;
import br.com.stockup.enums.PerfilUsuario;
import br.com.stockup.model.Loja;
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
        //criar o usuario
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(PerfilUsuario.PROPRIETARIO);

        usuarioRepository.save(usuario);

        Loja loja = new Loja();

        loja.setNome(dto.getNome());
        loja.setTipo(dto.getTipoLoja());
        loja.setUsuario(usuario);

        lojaRepository.save(loja);
    }
}
